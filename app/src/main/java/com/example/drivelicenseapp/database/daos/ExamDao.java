package com.example.drivelicenseapp.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.drivelicenseapp.database.entities.Exam;

import java.util.List;

@Dao
public interface ExamDao {
    @Insert
    long insert(Exam exam);

    @Update
    void update(Exam exam);

    @Delete
    void delete(Exam exam);

    @Query("SELECT * FROM exams")
    LiveData<List<Exam>> getAllExams();

    @Query("SELECT * FROM exams WHERE id = :examId")
    LiveData<Exam> getExamById(int examId);
}
