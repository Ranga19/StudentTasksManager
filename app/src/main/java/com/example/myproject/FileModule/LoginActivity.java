package com.example.myproject.FileModule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    TextView registerTv;
    Button loginBtn;
    EditText editTextEmail, editTextPassword;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);

        registerTv = (TextView) findViewById(R.id.registerTv);
        loginBtn = findViewById(R.id.loginNext);

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterUser.class));
            }
        });

        editTextEmail = (EditText) findViewById(R.id.mailet);
        editTextPassword = (EditText) findViewById(R.id.passet);
        auth= FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }

    private void userLogin() {
        String mail = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (mail.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;
        }
        auth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        startActivity(new Intent(LoginActivity.this,EnterColorCombination.class));
                    }
                    else{
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this,"check email to verify",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(LoginActivity.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
            }
        });
    }
}