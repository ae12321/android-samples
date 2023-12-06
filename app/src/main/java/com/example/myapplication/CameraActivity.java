package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CameraActivity extends AppCompatActivity {

    Button btnModal;

    TextView textResponse;
    /**
     *
     */
    private Runnable positive = () -> {
        OkHttpClient client = new OkHttpClient();

        System.out.println("---------- get normal ----------");
        try {
            String url = "https://httpbin.org/get";
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();

            System.out.println("---------- body ----------");
            final String body = response.body().string();
            System.out.println(body);

            final Handler mainThread = new Handler(Looper.getMainLooper());
            mainThread.post(() -> {
                try {
                    textResponse.setText(body);
                    Toast.makeText(CameraActivity.this, "通知しました", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CameraActivity.this, "通知できませんでした", Toast.LENGTH_SHORT).show();
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

    /**
     *
     */
    private Runnable negative = () -> {
        final Handler mainThread = new Handler(Looper.getMainLooper());
        // 回避: java.lang.NullPointerException: Can't toast on a thread that has not called Looper.prepare()
        mainThread.post(() -> {
            Toast.makeText(CameraActivity.this, "通知できませんでした", Toast.LENGTH_SHORT).show();
        });
    };

    /**
     *
     */
    public static void showThreadInfo() {
        Thread thread = Thread.currentThread();
        String threadName = thread.getName();
        System.out.println("threadName: " + threadName);    // threadName: main
    }

    /**
     *
     * @param context
     * @param positive
     * @param negative
     * @return
     */
    public AlertDialog.Builder createModal(Context context, Runnable positive, Runnable negative) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("タイトルです");
        builder.setMessage("メッセージです");
        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            new Thread(positive).start();
        });
        builder.setNegativeButton("NO", (dialogInterface, i) -> {
            new Thread(negative).start();
        });

        return builder;
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btnModal = findViewById(R.id.btnModal);
        textResponse = findViewById(R.id.textResponse);
        showThreadInfo();   // threadName: main
        btnModal.setOnClickListener(view -> {

            System.out.println("000");
            showThreadInfo();       // threadName: main


            AlertDialog.Builder modal = createModal(CameraActivity.this, positive, negative);
            modal.show();
//            new AlertDialog.Builder(CameraActivity.this)
//                    .setTitle("タイトルです")
//                    .setMessage("メッセージです")
//                    .setPositiveButton("OK", (dialogInterface, i) -> {
//
//                        // https://qiita.com/yasumodev/items/a5cbac4278cd14c74899
//                        // https://stackoverflow.com/questions/16268693/running-code-on-the-main-thread-from-a-secondary-thread
//                        Runnable executeRequest = () -> {
//                            OkHttpClient client = new OkHttpClient();
//                            System.out.println("---------- get normal ----------");
//                            try {
//                                System.out.println(111);
//                                showThreadInfo();       // threadName: Thread-2
//
//                                String url = "https://httpbin.org/get";
//                                Request request = new Request.Builder().url(url).build();
//                                Response response = client.newCall(request).execute();
//                                System.out.println("---------- body ----------");
//                                final String body = response.body().string();
//                                System.out.println(body);
//                                final Handler main = new Handler(Looper.getMainLooper());
//                                main.post(() -> {
//
//                                    System.out.println(222);
//                                    showThreadInfo();       // threadName: main
//
//                                    try {
//                                        textResponse.setText(body);
//                                    } catch (Exception e){
//                                        e.printStackTrace();
//                                    }
//                                });
//                                System.out.println("---------- headers ----------");
//                                System.out.println(response.headers());
//                                System.out.println("---------- code ----------");
//                                System.out.println(response.code());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        };
//
//                        System.out.println(333);
//                        showThreadInfo();       // threadName: main
//
//                        new Thread(executeRequest).start();
//                    })
//                    .show();
        });
    }
}