package com.example.drivelicenseapp.database.converters;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StringArrayConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static String[] fromString(String value) {
        if (value == null) return new String[0];
        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> list = gson.fromJson(value, listType);
        return list.toArray(new String[0]);
    }

    @TypeConverter
    public static String toString(String[] array) {
        if (array == null) return "[]";
        List<String> list = new ArrayList<>();
        for (String s : array) {
            list.add(s);
        }
        return gson.toJson(list);
    }
}