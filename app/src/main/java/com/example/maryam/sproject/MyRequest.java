package com.example.maryam.sproject;

import org.json.JSONException;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyRequest {

    public void GetCall(String URL, final OkHttpCallback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    callback.onResponse(call, response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void PostCall(String URL, Map<String, String> parameter, final OkHttpCallback callback) {

        FormBody.Builder formBuilder = new FormBody.Builder();

        for (Map.Entry<String, String> entry : parameter.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            formBuilder.add(entry.getKey(), entry.getValue());
        }
        RequestBody body = formBuilder.build();
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call, e);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    callback.onResponse(call, response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}


