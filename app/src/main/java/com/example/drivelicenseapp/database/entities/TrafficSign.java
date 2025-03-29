package com.example.drivelicenseapp.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "traffic_signs") // Tên bảng trong database
public class TrafficSign {
    @PrimaryKey(autoGenerate = true) // Khóa chính tự động sinh
    public int id;

    public String name; // Tên biển báo
    public String code; // Mã biển báo
    public int imageResId; // ID resource hình ảnh

    // Constructor
    public TrafficSign() {} // Constructor mặc định
    public TrafficSign(String name, String code, int imageResId) {
        this.name = name;
        this.code = code;
        this.imageResId = imageResId;
    }

    // Getter methods (Room cần getter cho các trường)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getImageResId() {
        return imageResId;
    }
}