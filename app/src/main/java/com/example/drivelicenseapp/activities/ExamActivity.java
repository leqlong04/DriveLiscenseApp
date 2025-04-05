package com.example.drivelicenseapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.drivelicenseapp.R;
import com.example.drivelicenseapp.adapters.QuestionAdapter;
import com.example.drivelicenseapp.database.entities.Question;
import com.example.drivelicenseapp.helpers.JsonHelper;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import android.util.Log;
public class ExamActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TextView tvTimer;
    private TextView tvQuestionCount;
    private MaterialButton btnPrevious;
    private MaterialButton btnNext;
    private MaterialButton btnSubmit;

    private List<Question> questions;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        // Đọc examId từ Intent
        Intent intent = getIntent();
        if (intent.hasExtra("examId")) {
            int examId = intent.getIntExtra("examId", 0);
            questions = loadExamQuestions(examId);
        } else {
            questions = generateRandomExam();
        }
        // Initialize views
        viewPager = findViewById(R.id.viewPager);
        tvTimer = findViewById(R.id.tvTimer);
        tvQuestionCount = findViewById(R.id.tvQuestionCount);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Setup sample questions


        // Setup ViewPager and Adapter
        QuestionAdapter adapter = new QuestionAdapter(this, questions);
        viewPager.setAdapter(adapter);

        // Setup navigation buttons
        setupNavigationControls();

        // Start timer
        startTimer();
    }

    private List<Question> loadExamQuestions(int examId) {
        List<Question> allQuestions = JsonHelper.loadQuestions(this);
        List<Question> examQuestions = new ArrayList<>();
        for (Question q : allQuestions) {
            if (q.examId == examId) examQuestions.add(q);
        }
        return examQuestions;
    }
    private List<Question> generateRandomExam() {
        List<Question> allQuestions = JsonHelper.loadQuestions(this);
        List<Question> criticals = new ArrayList<>();
        List<Question> nonCriticals = new ArrayList<>();

        for (Question q : allQuestions) {
            if (q.isCritical) criticals.add(q);
            else nonCriticals.add(q);
        }

        Collections.shuffle(nonCriticals);
        List<Question> selected = new ArrayList<>(nonCriticals.subList(0, Math.min(24, nonCriticals.size())));

        if (!criticals.isEmpty()) {
            selected.add(criticals.get(new Random().nextInt(criticals.size())));
        }

        Collections.shuffle(selected);
        return selected;
    }
    private List<Question> createSampleQuestions() {
        List<Question> sampleQuestions = new ArrayList<>();

        for (int i = 1; i <= 25; i++) {
            Question question = new Question();
            question.id = i;
            question.content = "Câu hỏi mẫu số " + i;
            question.correctAnswer = "1";
            question.options = new String[]{"Đáp án 1", "Đáp án 2", "Đáp án 3", "Đáp án 4"};
            question.examId = 1; // Thêm examId

            sampleQuestions.add(question);
        }
        return sampleQuestions;
    }

    private void setupNavigationControls() {
        // Button click listeners
        btnPrevious.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() > 0) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });

        btnNext.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() < questions.size() - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });

        btnSubmit.setOnClickListener(v -> submitExam());

        // ViewPager page change callback
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateQuestionCounter(position);
                updateNavigationButtons(position);
            }
        });
    }

    private void updateQuestionCounter(int position) {
        tvQuestionCount.setText(String.format("Câu %d/%d", position + 1, questions.size()));
    }

    private void updateNavigationButtons(int position) {
        btnPrevious.setEnabled(position > 0);
        btnNext.setEnabled(position < questions.size() - 1);
    }

    private void startTimer() {
        timer = new CountDownTimer(20 * 60 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                String time = String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                tvTimer.setText(time);
            }

            public void onFinish() {
                submitExam();
            }
        }.start();
    }

    private void submitExam() {
        if (timer != null) {
            timer.cancel();
        }
        calculateScore();
    }

    private void calculateScore() {
        int correct = 0;
        boolean hasCriticalWrong = false;

        for (Question q : questions) {
            if (q.userAnswer == null || q.userAnswer.isEmpty()) {
                Log.d("SCORING", "Q" + q.id + " | Chưa trả lời");
                continue;
            }

            boolean isCorrect = q.userAnswer.trim().equals(q.correctAnswer.trim());

            Log.d("SCORING", "Q" + q.id
                    + " | User: '" + q.userAnswer + "'"
                    + " | Correct: '" + q.correctAnswer + "'"
                    + " | Result: " + isCorrect);

            if (q.isCritical && !isCorrect) hasCriticalWrong = true;
            if (isCorrect) correct++;
        }

        Log.d("FINAL_SCORE", "Correct: " + correct + "/" + questions.size());

        boolean passed = !hasCriticalWrong && correct >= 21;

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("correct", correct); // Truyền số câu đúng
        intent.putExtra("total", questions.size());
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}