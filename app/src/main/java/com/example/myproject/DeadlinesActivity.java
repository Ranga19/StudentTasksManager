package com.example.myproject;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myproject.activeProject.model.ProjectActivity;
import com.example.myproject.dbDeadlines.Deadline;
import com.example.myproject.dbDeadlines.DeadlineAdapter;

import com.example.myproject.dbDeadlines.DeadlineAppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DeadlinesActivity extends AppCompatActivity {
    private DeadlineAdapter adp;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlines);
        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.addNewDeadline);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Enter about deadline");
        View v=getLayoutInflater().inflate(R.layout.cust,null);
        EditText et1=(EditText)v.findViewById(R.id.et1Deadline);
        Button ok=v.findViewById(R.id.sub);
        builder.setView(v);
        dialog=builder.create();
        ok.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                saveNewUser(et1.getText().toString());
                startActivityForResult(new Intent(DeadlinesActivity.this, DeadlinesActivity.class),100);
                dialog.dismiss();
            }
        });
        initRecyclerView();
        loadDeadlineList();
    }

    private void loadDeadlineList() {
        DeadlineAppDatabase db= DeadlineAppDatabase.getDbInstance(this.getApplicationContext());
        List<Deadline> deadlineList= db.deadlineDao().getAllDeadlines();
        adp.setDeadlineList(deadlineList);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView=findViewById(R.id.recyclerview_deadlines);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adp=new DeadlineAdapter(this);
        recyclerView.setAdapter(adp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100){
            loadDeadlineList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveNewUser(String firstName){
        DeadlineAppDatabase db=DeadlineAppDatabase.getDbInstance(this.getApplicationContext());
        Deadline user=new Deadline();
        user.deadlineTitleCol=firstName;
        user.deadlineDateCol="";
        db.deadlineDao().insertDeadline(user);
        finish();
    }
}