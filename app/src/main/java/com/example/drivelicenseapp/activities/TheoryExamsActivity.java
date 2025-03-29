package com.example.drivelicenseapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivelicenseapp.R;

import java.util.Arrays;
import java.util.List;

public class TheoryExamsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_exams);

        RecyclerView recyclerView = findViewById(R.id.rvExams);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<Integer> exams = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        ExamAdapter adapter = new ExamAdapter(exams);
        recyclerView.setAdapter(adapter);
    }

    private static class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {
        private final List<Integer> exams;

        public ExamAdapter(List<Integer> exams) {
            this.exams = exams;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_exam, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int examNumber = exams.get(position);
            holder.tvExamTitle.setText("Đề số " + examNumber);

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), ExamActivity.class);
                intent.putExtra("examId", examNumber);
                v.getContext().startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return exams.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvExamTitle;

            ViewHolder(View itemView) {
                super(itemView);
                tvExamTitle = itemView.findViewById(R.id.tvExamTitle);
            }
        }
    }
}