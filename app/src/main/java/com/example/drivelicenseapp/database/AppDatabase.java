package com.example.drivelicenseapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.drivelicenseapp.database.converters.Converters;
import com.example.drivelicenseapp.database.daos.ExamDao;
import com.example.drivelicenseapp.database.daos.QuestionDao;
import com.example.drivelicenseapp.database.daos.ResultDao;
import com.example.drivelicenseapp.database.daos.TrafficSignDao;
import com.example.drivelicenseapp.database.entities.Exam;
import com.example.drivelicenseapp.database.entities.Question;
import com.example.drivelicenseapp.database.entities.Result;
import com.example.drivelicenseapp.database.entities.TrafficSign;

@Database(
        entities = {
                Question.class,
                TrafficSign.class,
                Result.class,
                Exam.class // Thêm entity Exam nếu cần
        },
        version = 1
)
@TypeConverters({Converters.class}) // Thêm converters nếu có trường dữ liệu đặc biệt
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();
    public abstract TrafficSignDao trafficSignDao();
    public abstract ResultDao resultDao();
    public abstract ExamDao examDao(); // Thêm DAO cho Exam nếu cần
}