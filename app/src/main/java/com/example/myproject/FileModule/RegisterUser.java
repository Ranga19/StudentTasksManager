package com.example.myproject.FileModule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {
    EditText mFullName,mEmail,mPassword;
    Button mRegisterBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mFullName=findViewById(R.id.fullName);
        mEmail=findViewById(R.id.Email);
        mPassword=findViewById(R.id.password);

        mRegisterBtn=findViewById(R.id.next);
        fAuth=FirebaseAuth.getInstance();

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String fullname=mFullName.getText().toString().trim();

                if(email.isEmpty()){
                    mEmail.setError("required");
                    mEmail.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    mPassword.setError("required");
                    mPassword.requestFocus();
                    return;
                }
                if(fullname.isEmpty()){
                    mFullName.setError("required");
                    mFullName.requestFocus();
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterUser.this,"user created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterUser.this,ColorAuth.class));
                        }
                        else{
                            Toast.makeText(RegisterUser.this,""+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}