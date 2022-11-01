package com.example.myproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myproject.db.AppDatabase;
import com.example.myproject.db.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TodoList extends AppCompatActivity {
    private UserListAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        FloatingActionButton btn=(FloatingActionButton) findViewById(R.id.addNewNote);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(TodoList.this, InsertNoteActivity.class),100);
            }
        });
        initRecyclerView();
        loadNotesList();
    }

    private void loadNotesList() {
        AppDatabase db=AppDatabase.getDbInstance(this.getApplicationContext());
        List<User> userList=db.userDao().getAllUsers();
        adp.setUserList(userList);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adp=new UserListAdapter(this);
        recyclerView.setAdapter(adp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100){
            loadNotesList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}