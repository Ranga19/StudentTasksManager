package com.example.myproject.FileModule;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class FileActivity extends AppCompatActivity {
    Button selectFile, upload;
    TextView notification;
    FirebaseStorage storage;
    FirebaseDatabase database;
    Uri pdfUri;
    ProgressDialog progressDialog;
    Button fetch;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPdf();
        } else
            Toast.makeText(FileActivity.this, "Please provide permission..", Toast.LENGTH_SHORT).show();
    }

    private void selectPdf() {
        Intent i = new Intent();
        i.setType("application/pdf");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, 86);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        fetch=findViewById(R.id.fetch);
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FileActivity.this,LoginActivity.class));
            }
        });

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        selectFile = findViewById(R.id.selectFile);

        upload = findViewById(R.id.upload);
        notification = findViewById(R.id.notification);

        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(FileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    selectPdf();
                else
                    ActivityCompat.requestPermissions(FileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pdfUri != null)
                    uploadFile(pdfUri);
                else Toast.makeText(FileActivity.this, "Select a file", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadFile(Uri pdfUri) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("uploading file..");
        progressDialog.show();
        progressDialog.setProgress(0);

        final String fname = System.currentTimeMillis() + ".pdf";
        final String fname1=System.currentTimeMillis()+"";
        StorageReference storageReference = storage.getReference();
        storageReference.child("Uploads").child(fname).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url = taskSnapshot.getStorage().getDownloadUrl().toString();
                        DatabaseReference reference = database.getReference();
                        reference.child(fname1).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                    Toast.makeText(FileActivity.this, "File successfullu uploaded", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(FileActivity.this, "failed to upload", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FileActivity.this, "failed to upload", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                int currentProgress = (int) (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            notification.setText("file selcted: " + data.getData().getLastPathSegment());
        } else
            Toast.makeText(FileActivity.this, "Please select file", Toast.LENGTH_SHORT).show();
    }
}
