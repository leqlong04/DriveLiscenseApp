package com.example.drivelicenseapp.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivelicenseapp.R;
import com.example.drivelicenseapp.database.entities.Question;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private final List<Question> questions;

    public ReviewAdapter(List<Question> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.bind(question);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvQuestion;
        private final TextView tvUserAnswer;
        private final TextView tvCorrectAnswer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            tvUserAnswer = itemView.findViewById(R.id.tvUserAnswer);
            tvCorrectAnswer = itemView.findViewById(R.id.tvCorrectAnswer);
        }

        public void bind(Question question) {
            tvQuestion.setText(question.getContent());

            // Hiển thị đáp án người dùng
            String userAnswer = "Đáp án của bạn: " + getAnswerText(question, question.userAnswer);
            tvUserAnswer.setText(userAnswer);
            tvUserAnswer.setTextColor(question.isCorrect() ? Color.GREEN : Color.RED);

            // Hiển thị đáp án đúng
            String correctAnswer = "Đáp án đúng: " + getAnswerText(question, question.correctAnswer);
            tvCorrectAnswer.setText(correctAnswer);
        }

        private String getAnswerText(Question question, String answer) {
            try {
                int index = Integer.parseInt(answer) - 1;
                return question.options[index];
            } catch (Exception e) {
                return "Không có đáp án";
            }
        }
    }
}
