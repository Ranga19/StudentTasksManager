package com.example.myproject.FileModule;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    RecyclerView recyclerView;
    Context context;
    ArrayList<String> items=new ArrayList<>();
    ArrayList<String> urls=new ArrayList<>();

    public void update(String name,String url){
        items.add(name);
        urls.add(url);
        notifyDataSetChanged();
    }

    public MyAdapter(RecyclerView recyclerView, Context context, ArrayList<String> items,ArrayList<String> urls) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.items = items;
        this.urls=urls;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameOfFile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfFile=itemView.findViewById(R.id.nameOfFile);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=recyclerView.getChildAdapterPosition(view);
                    Intent intent=new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urls.get(position)));
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameOfFile.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
