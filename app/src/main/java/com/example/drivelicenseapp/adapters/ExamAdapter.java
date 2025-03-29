package com.example.drivelicenseapp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.drivelicenseapp.R;
import com.example.drivelicenseapp.activities.ExamActivity;
import com.example.drivelicenseapp.database.entities.Exam;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {
    private List<Integer> examIds;

    public ExamAdapter(List<Integer> examIds) {
        this.examIds = examIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exam, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvExamTitle.setText("Đề số " + examIds.get(position));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ExamActivity.class);
            intent.putExtra("examId", examIds.get(position));
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return examIds.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvExamTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvExamTitle = itemView.findViewById(R.id.tvExamTitle);
        }
    }
}