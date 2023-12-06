package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CameraActivity extends AppCompatActivity {

    Button btnModal;

    public static void showThreadInfo() {
        Thread thread = Thread.currentThread();
        String threadName = thread.getName();
        System.out.println("threadName: " + threadName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btnModal = findViewById(R.id.btnModal);
        showThreadInfo();
        btnModal.setOnClickListener(view -> {
            new AlertDialog.Builder(CameraActivity.this)
                    .setTitle("タイトルです")
                    .setMessage("メッセージです")
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        final Handler handler = new Handler();
                    })
                    .show();
        });
    }
}