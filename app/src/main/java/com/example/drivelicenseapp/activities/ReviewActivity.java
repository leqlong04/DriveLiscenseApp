package com.example.drivelicenseapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivelicenseapp.R;
import com.example.drivelicenseapp.adapters.ReviewAdapter;
import com.example.drivelicenseapp.database.entities.Question;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ReviewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private FloatingActionButton fabHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        List<Question> questions = (List<Question>) getIntent().getSerializableExtra("questions");
        fabHome = findViewById(R.id.fabHome);
        recyclerView = findViewById(R.id.rvReview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReviewAdapter(questions);
        recyclerView.setAdapter(adapter);

        fabHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}