package com.example.myproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle,tvDes,tvDate;
    View ivPriority;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle=itemView.findViewById(R.id.notesTitle);
        tvDes=itemView.findViewById(R.id.des);
        tvDate=itemView.findViewById(R.id.date);
        ivPriority=itemView.findViewById(R.id.priority);
    }
}
