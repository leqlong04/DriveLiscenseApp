package com.example.drivelicenseapp.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.drivelicenseapp.database.converters.Converters;
import java.util.Date;

@Entity(tableName = "exams")
@TypeConverters({Converters.class})
public class Exam {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String title;

    @TypeConverters({Converters.class})
    public Date createdDate;

    public int totalQuestions;
}