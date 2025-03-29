package com.example.drivelicenseapp.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.drivelicenseapp.R;
import com.example.drivelicenseapp.adapters.TrafficSignAdapter;
import com.example.drivelicenseapp.database.entities.TrafficSign;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class TrafficSignsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TrafficSignAdapter adapter;
    private FloatingActionButton fabHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_signs);

        // Ánh xạ view
        recyclerView = findViewById(R.id.recyclerView);
        fabHome = findViewById(R.id.fabHome); // Thêm FAB

        // Thiết lập RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Dữ liệu mẫu
        List<TrafficSign> signs = new ArrayList<>();
        signs.add(new TrafficSign("Cấm đi ngược chiều", "P.124", R.drawable.sign_sample));
        signs.add(new TrafficSign("Cấm dừng đỗ", "P.125", R.drawable.sign_sample));

        adapter = new TrafficSignAdapter(signs);
        recyclerView.setAdapter(adapter);

        // Xử lý click nút home
        fabHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}