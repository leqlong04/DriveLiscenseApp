package com.example.drivelicenseapp.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.example.drivelicenseapp.R;
import com.example.drivelicenseapp.database.entities.Question;

import java.io.File;
import java.io.Serializable;
import java.lang.annotation.Target;

public class QuestionFragment extends Fragment {

    private static final String ARG_QUESTION = "question";
    private Question question;

    public static QuestionFragment newInstance(Question question) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUESTION, (Serializable) question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = (Question) getArguments().getSerializable(ARG_QUESTION);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        TextView tvQuestion = view.findViewById(R.id.tvQuestion);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        ImageView ivQuestionImage = view.findViewById(R.id.ivQuestionImage);
        Log.d("IMAGE_DEBUG", "Image path: " + question.imagePath);
        File imageFile = new File(question.imagePath);
        Log.d("IMAGE_DEBUG", "File exists: " + imageFile.exists());
        // Xử lý ảnh
        if (question.imagePath != null && !question.imagePath.isEmpty()) {
            ivQuestionImage.setVisibility(View.VISIBLE);

            // Thêm logging để debug

            // Load ảnh từ file (đã copy từ assets)
            Glide.with(this)
                    .load(new File(question.imagePath))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull com.bumptech.glide.request.target.Target<Drawable> target, boolean isFirstResource) {
                            Log.e("GLIDE_ERROR", "Load failed: " + e.getMessage());
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, com.bumptech.glide.request.target.Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }


                    })
                    .into(ivQuestionImage);
        } else {
            ivQuestionImage.setVisibility(View.GONE);
            Log.d("IMAGE_DEBUG", "No image for this question");
        }

        // Phần còn lại giữ nguyên
        tvQuestion.setText(question.content);

        for (int i = 0; i < question.options.length; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(question.options[i]);
            radioButton.setId(View.generateViewId());
            radioGroup.addView(radioButton);
        }

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int selectedIndex = radioGroup.indexOfChild(group.findViewById(checkedId));
            question.userAnswer = String.valueOf(selectedIndex + 1);
            Log.d("ANSWER", "Q" + question.id + " Chọn: " + question.userAnswer);
        });

        return view;
    }
}