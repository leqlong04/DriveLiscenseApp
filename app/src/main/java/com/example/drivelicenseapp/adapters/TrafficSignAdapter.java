package com.example.drivelicenseapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.drivelicenseapp.R;
import com.example.drivelicenseapp.database.entities.TrafficSign;
import java.util.List;

public class TrafficSignAdapter extends RecyclerView.Adapter<TrafficSignAdapter.ViewHolder> {
    private List<TrafficSign> signs;

    public TrafficSignAdapter(List<TrafficSign> signs) {
        this.signs = signs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_traffic_sign, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrafficSign sign = signs.get(position);
        holder.tvName.setText(sign.getName());
        holder.tvCode.setText(sign.getCode());
        holder.ivSign.setImageResource(sign.getImageResId());
    }

    @Override
    public int getItemCount() {
        return signs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSign;
        TextView tvName;
        TextView tvCode;

        public ViewHolder(View itemView) {
            super(itemView);
            ivSign = itemView.findViewById(R.id.ivSign);
            tvName = itemView.findViewById(R.id.tvName);
            tvCode = itemView.findViewById(R.id.tvCode);
        }
    }
}