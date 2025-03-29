package com.example.drivelicenseapp.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.drivelicenseapp.database.entities.TrafficSign;
import java.util.List;

@Dao
public interface TrafficSignDao {
    @Insert
    void insertAll(TrafficSign... signs); // Chèn nhiều biển báo

    @Query("SELECT * FROM traffic_signs") // Lấy tất cả biển báo
    LiveData<List<TrafficSign>> getAllSigns();

    @Query("SELECT * FROM traffic_signs WHERE code LIKE '%' || :searchQuery || '%' OR name LIKE '%' || :searchQuery || '%'") // Tìm kiếm
    LiveData<List<TrafficSign>> searchSigns(String searchQuery);
}