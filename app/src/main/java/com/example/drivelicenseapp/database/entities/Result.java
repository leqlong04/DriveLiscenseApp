package com.example.drivelicenseapp.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.drivelicenseapp.database.converters.Converters;
import java.util.Date;

@Entity(tableName = "results")
@TypeConverters({Converters.class})
public class Result {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int examId;
    public int correctAnswers;
    public int totalQuestions;

    @TypeConverters({Converters.class})
    public Date date;

    public boolean isPassed;
}