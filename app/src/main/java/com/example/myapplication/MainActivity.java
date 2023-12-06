package com.example.myapplication;

import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnCamera;
    TextView textView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCamera = findViewById(R.id.btnCamera);
        textView1 = findViewById(R.id.textView);
        System.out.println(btnCamera);
        btnCamera.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CameraActivity.class));
        });

    }
}