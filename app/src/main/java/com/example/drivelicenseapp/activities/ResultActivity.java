package com.example.drivelicenseapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.drivelicenseapp.R;
import com.google.android.material.button.MaterialButton;

import java.io.Serializable;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvResult = findViewById(R.id.tvResult);

        // Sửa từ getIntExtra thay vì getBoolean
        int correct = getIntent().getIntExtra("correct", 0);
        int total = getIntent().getIntExtra("total", 25);

        tvScore.setText(String.format("Đúng: %d/%d", correct, total));
        tvResult.setText(correct >= 21 ? "ĐẠT" : "KHÔNG ĐẠT");

        MaterialButton btnRetry = findViewById(R.id.btnRetry);
        MaterialButton btnHome = findViewById(R.id.btnHome);
        MaterialButton btnReview = findViewById(R.id.btnReview);
        btnReview.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReviewActivity.class);
            intent.putExtra("questions", (Serializable) getIntent().getSerializableExtra("questions"));
            startActivity(intent);
        });
        btnRetry.setOnClickListener(v -> {
            // Xử lý thử lại
            Intent intent = new Intent(this, ExamActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Xóa stack cũ
            startActivity(intent);
            finish();
        });

        btnHome.setOnClickListener(v -> {
            // Về màn hình chính
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}