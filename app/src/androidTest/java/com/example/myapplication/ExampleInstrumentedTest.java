package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    OkHttpClient client = new OkHttpClient();

    @Test
    public void case1() {
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
    }
    @Test
    public void case2() {
        System.out.println("---------- post normal ----------");
        try {
            String url = "https://httpbin.org/post";
            JSONObject json = new JSONObject();
            json.put("id", 1);
            json.put("name", "abc");
            RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json"));

            Request request = new Request.Builder().url(url).post(body).build();
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
    }


    @Test
    public void case3() {
        System.out.println("---------- put normal ----------");
        try {
            String url = "https://httpbin.org/put";
            JSONObject json = new JSONObject();
            json.put("id", 1);
            json.put("name", "abc");
            RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json"));

            Request request = new Request.Builder().url(url).put(body).build();
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
    }


    @Test
    public void case4() {
        System.out.println("---------- delete normal ----------");
        try {
            String url = "https://httpbin.org/delete";

            Request request = new Request.Builder().url(url).delete().build();
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
    }

    @Test
    public void case10() {
        System.out.println("---------- get 404 ----------");
        try {
            String url = "https://httpbin.org/status/500";

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
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.myapplication", appContext.getPackageName());

    }
}