package com.example.drivelicenseapp.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.drivelicenseapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnExam = findViewById(R.id.btnExam);
        Button btnTrafficSigns = findViewById(R.id.btnTrafficSigns);
        Button btnTheory = findViewById(R.id.btnTheory);
        btnExam.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExamActivity.class);
            startActivity(intent);
        });

        btnTrafficSigns.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TrafficSignsActivity.class);
            startActivity(intent);
        });
        btnTheory.setOnClickListener(v -> {
            startActivity(new Intent(this, TheoryExamsActivity.class));
        });
    }
}