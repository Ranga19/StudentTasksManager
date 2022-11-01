package com.example.myproject.dbDeadlines;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myproject.R;

import java.util.List;

public class DeadlineAdapter extends RecyclerView.Adapter<DeadlineHolder> {
    private Context context;
    private List<Deadline> deadlineList;
    public DeadlineAdapter(Context context){
        this.context= context;
    }
    public void setDeadlineList(List<Deadline> deadlineList){
        this.deadlineList=deadlineList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DeadlineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeadlineHolder(LayoutInflater.from(context).inflate(R.layout.row_deadline,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeadlineHolder holder, int position) {
        holder.dlTitle.setText(deadlineList.get(position).deadlineTitleCol);
        holder.dlDate.setText(deadlineList.get(position).deadlineDateCol);
    }

    @Override
    public int getItemCount() {
        return deadlineList.size();
    }
}
