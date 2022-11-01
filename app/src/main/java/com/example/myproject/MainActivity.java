package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myproject.FileModule.FileActivity;
import com.example.myproject.activeProject.model.ProjectActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView todoListCard=(CardView) findViewById(R.id.todoListCard);
        CardView deadLinesCard=(CardView) findViewById(R.id.deadLinesCard);
        CardView projectCard=(CardView) findViewById(R.id.projectCard);
        CardView filesCard=(CardView) findViewById(R.id.filesCard);
        todoListCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),TodoList.class));
            }
        });
        deadLinesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DeadlinesActivity.class));
            }
        });
        projectCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProjectActivity.class));
            }
        });
        filesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FileActivity.class));
            }
        });
    }
}