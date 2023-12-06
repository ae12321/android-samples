package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CameraActivity extends AppCompatActivity {

    Button btnModal;

    TextView textResponse;

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
        textResponse = findViewById(R.id.textResponse);
        showThreadInfo();   // threadName: main
        btnModal.setOnClickListener(view -> {
            new AlertDialog.Builder(CameraActivity.this)
                    .setTitle("タイトルです")
                    .setMessage("メッセージです")
                    .setPositiveButton("OK", (dialogInterface, i) -> {

                        // https://qiita.com/yasumodev/items/a5cbac4278cd14c74899
                        Runnable executeRequest = () -> {
                            OkHttpClient client = new OkHttpClient();
                            System.out.println("---------- get normal ----------");
                            try {
                                System.out.println(111);
                                showThreadInfo();

                                String url = "https://httpbin.org/get";
                                Request request = new Request.Builder().url(url).build();
                                Response response = client.newCall(request).execute();
                                System.out.println("---------- body ----------");
                                final String body = response.body().string();
                                System.out.println(body);
                                final Handler main = new Handler(Looper.getMainLooper());
                                main.post(() -> {

                                    System.out.println(222);
                                    showThreadInfo();

                                    try {
                                        textResponse.setText(body);
                                    } catch (Exception e){
                                        e.printStackTrace();
                                    }
                                });
                                System.out.println("---------- headers ----------");
                                System.out.println(response.headers());
                                System.out.println("---------- code ----------");
                                System.out.println(response.code());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        };

                        System.out.println(333);
                        showThreadInfo();

                        new Thread(executeRequest).start();
                    })
                    .show();
        });
    }
}