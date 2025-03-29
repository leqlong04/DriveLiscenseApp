package com.example.drivelicenseapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.drivelicenseapp.database.entities.Question;
import com.example.drivelicenseapp.fragments.QuestionFragment;

import java.util.List;

public class QuestionAdapter extends FragmentStateAdapter {
    private final List<Question> questions;

    public QuestionAdapter(@NonNull FragmentActivity fragmentActivity, List<Question> questions) {
        super(fragmentActivity);
        this.questions = questions;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return QuestionFragment.newInstance(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}