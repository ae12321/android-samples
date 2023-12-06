package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CameraActivity extends AppCompatActivity {

    Button btnModal;

    public static void showThreadInfo() {
        Thread thread = Thread.currentThread();
        String threadName = thread.getName();
        System.out.println("threadName: " + threadName);    // threadName: main
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btnModal = findViewById(R.id.btnModal);
        showThreadInfo();   // threadName: main
        btnModal.setOnClickListener(view -> {
            new AlertDialog.Builder(CameraActivity.this)
                    .setTitle("タイトルです")
                    .setMessage("メッセージです")
                    .setPositiveButton("OK", (dialogInterface, i) -> {

                        Runnable executeRequest = () -> {
                            OkHttpClient client = new OkHttpClient();
                            System.out.println("---------- get normal ----------");
                            try {
                                String url = "https://httpbin.org/get";
                                Request request = new Request.Builder().url(url).build();
                                Response response = client.newCall(request).execute();
                                System.out.println("---------- body ----------");
                                System.out.println(response.body().string());
                                System.out.println("---------- headers ----------");
                                System.out.println(response.headers());
                                System.out.println("---------- code ----------");
                                System.out.println(response.code());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        };
                        new Thread(executeRequest).start();
                    })
                    .show();
        });
    }
}