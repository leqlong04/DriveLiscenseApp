package com.example.drivelicenseapp.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.drivelicenseapp.database.entities.Question;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insertAll(Question... questions);

    @Update
    void update(Question question);

    @Query("SELECT * FROM questions WHERE id = :questionId")
    LiveData<Question> getQuestionById(int questionId);

    @Query("SELECT * FROM questions WHERE examId = :examId")
    LiveData<List<Question>> getQuestionsByExam(int examId);
}
