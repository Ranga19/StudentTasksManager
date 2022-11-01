package com.example.myproject.FileModule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myproject.FileModule.LoginActivity;
import com.example.myproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ColorAuth extends AppCompatActivity {
    EditText mFullName,mEmail,mPassword;
    ImageView red,green,blue;
    EditText editText;
    private String code="";
    private String codeToShow="";
    ImageView reset;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_auth);
        red=findViewById(R.id.redOval);
        green=findViewById(R.id.greenOval);
        blue=findViewById(R.id.blueOval);
        editText=findViewById(R.id.etcode);
        editText.setEnabled(false);
        reset=findViewById(R.id.resetImg);
        fAuth=FirebaseAuth.getInstance();

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

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeToShow="";
                code="";
                editText.setText("");
            }
        });

    }

    public void registerBtn(View view) {
        SharedPreferences spf=getSharedPreferences("myspf", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=spf.edit();
        editor.putString("colorCode",code);
        editor.commit();
        startActivity(new Intent(ColorAuth.this, LoginActivity.class));
    }
}