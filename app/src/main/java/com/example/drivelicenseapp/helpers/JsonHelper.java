package com.example.drivelicenseapp.helpers;

import android.content.Context;
import android.util.Log;

import com.example.drivelicenseapp.database.entities.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
public class JsonHelper {
    private static final String TAG = "JsonHelper";

    private static String processImagePath(Context context, String rawPath) {
        // Sửa điều kiện check prefix thành "assets:"
        if (rawPath.startsWith("asset:")) {
            return copyAssetToInternalStorage(context, rawPath.replace("asset:", ""));
        }
        return rawPath;
    }

    private static String copyAssetToInternalStorage(Context context, String assetPath) {
        try {
            // Tạo thư mục đích nếu chưa tồn tại
            File outputDir = new File(context.getFilesDir(), "images");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            // Tách tên file từ đường dẫn
            String filename = assetPath.substring(assetPath.lastIndexOf("/") + 1);
            File outputFile = new File(outputDir, filename);

            // Copy file
            try (InputStream is = context.getAssets().open(assetPath);
                 FileOutputStream os = new FileOutputStream(outputFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            }

            return outputFile.getAbsolutePath();
        } catch (IOException e) {
            Log.e("IMAGE_COPY", "Error copying: " + assetPath, e);
            return "";
        }
    }
    public static List<Question> loadQuestions(Context context) {
        List<Question> questions = new ArrayList<>();
        try {
            // Mở file JSON từ assets
            InputStream is = context.getAssets().open("json/questions.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            // Parse JSON thành danh sách Question
            Type listType = new TypeToken<List<Question>>() {
            }.getType();
            questions = new Gson().fromJson(reader, listType);

            // Xử lý đường dẫn ảnh cho từng câu hỏi
            for (Question q : questions) {
                if (q.imagePath != null && !q.imagePath.isEmpty()) {
                    Log.d(TAG, "Processing image for Q" + q.id + ": " + q.imagePath);
                    String processedPath = processImagePath(context, q.imagePath);
                    Log.d(TAG, "Processed path: " + processedPath);
                    q.imagePath = processedPath;
                } else {
                    Log.w(TAG, "Q" + q.id + " has no image!"); // Cảnh báo câu hỏi không có ảnh
                }
            }
            return questions;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}