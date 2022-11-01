package com.example.myproject.dbDeadlines;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;

public class DeadlineHolder extends RecyclerView.ViewHolder {
    TextView dlTitle,dlDate;

    public DeadlineHolder(@NonNull View itemView) {
        super(itemView);
        dlTitle=itemView.findViewById(R.id.tvDlTitle);
        dlDate=itemView.findViewById(R.id.tvDltime);
    }
}
