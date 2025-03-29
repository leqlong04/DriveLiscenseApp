package com.example.drivelicenseapp.database.converters;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Date;

public class Converters {
    private static final Gson gson = new Gson();

    // Xử lý Date
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    // Xử lý mảng String
    @TypeConverter
    public static String[] fromString(String value) {
        return new Gson().fromJson(value, String[].class);
    }

    @TypeConverter
    public static String toString(String[] array) {
        return new Gson().toJson(array);
    }
}