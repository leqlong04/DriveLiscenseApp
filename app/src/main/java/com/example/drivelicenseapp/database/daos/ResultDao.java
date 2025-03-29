package com.example.drivelicenseapp.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.drivelicenseapp.database.entities.Result;

import java.util.List;

@Dao
public interface ResultDao {
    @Insert
    void insert(Result result);

    @Query("SELECT * FROM results ORDER BY date DESC")
    LiveData<List<Result>> getAllResults();

    @Query("SELECT * FROM results WHERE examId = :examId")
    LiveData<List<Result>> getResultsByExam(int examId);
}