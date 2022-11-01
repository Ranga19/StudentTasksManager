package com.example.myproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.db.User;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<User> userList;
    public UserListAdapter(Context context){
        this.context= context;
    }
    public void setUserList(List<User> userList){
        this.userList=userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(userList.get(position).titleCol);
        holder.tvDes.setText(userList.get(position).desCol);
        holder.tvDate.setText(userList.get(position).dateCol);

        User user=userList.get(position);
        if(user.priorityCol.equals("1"))
            holder.ivPriority.setBackgroundResource(R.drawable.red_oval);
        else if(user.priorityCol.equals("2"))
            holder.ivPriority.setBackgroundResource(R.drawable.yellow_oval);
        else
            holder.ivPriority.setBackgroundResource(R.drawable.green_oval);
    }
    @Override
    public int getItemCount() {
        return userList.size();
    }
}
