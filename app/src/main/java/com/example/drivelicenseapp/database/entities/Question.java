package com.example.drivelicenseapp.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.drivelicenseapp.database.converters.Converters;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(
        tableName = "questions",
        foreignKeys = @ForeignKey(
                entity = Exam.class,
                parentColumns = "id",
                childColumns = "examId",
                onDelete = ForeignKey.CASCADE
        )
)
@TypeConverters({Converters.class})
public class Question implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String content;
    @SerializedName("images") //load
    public String imagePath="";

    @NonNull
    public String correctAnswer;

    @NonNull
    public String[] options;

    @NonNull
    public String userAnswer = "";
    public boolean isCritical = false;
    public int examId;

    // Constructor mặc định (bắt buộc cho Room)
    public Question() {}

    // Getter/Setter cho các trường cần thiết
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}