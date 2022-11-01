package com.example.myproject.FileModule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class EnterColorCombination extends AppCompatActivity {
    ImageView red,green,blue;
    EditText editText;
    private String code="";
    private String codeToShow="";
    private String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_color_combination);
        editText=(EditText) findViewById(R.id.etcodeLogin);

        SharedPreferences spf=getSharedPreferences("myspf", Context.MODE_PRIVATE);
        res=spf.getString("colorCode",null);

        red=findViewById(R.id.redOvalLogin);
        green=findViewById(R.id.greenOvalLogin);
        blue=findViewById(R.id.blueOvalLogin);
        editText=findViewById(R.id.etcodeLogin);
        editText.setEnabled(false);

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code+="0";
                codeToShow+="*";
                editText.setText(codeToShow);
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code+="1";
                codeToShow+="*";
                editText.setText(codeToShow);
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code+="2";
                codeToShow+="*";
                editText.setText(codeToShow);
            }
        });
        Button btn=findViewById(R.id.loginBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        ImageView reset=findViewById(R.id.resetImgLogin);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code="";
                codeToShow="";
                editText.setText("");
            }
        });
    }

    private void login() {
        if(code.equals(res)){
            Toast.makeText(EnterColorCombination.this,"Login successful",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EnterColorCombination.this,MyRecyclerViewActivity.class));
        }
        else
            Toast.makeText(EnterColorCombination.this,"Wrong color combination",Toast.LENGTH_SHORT).show();
    }
}