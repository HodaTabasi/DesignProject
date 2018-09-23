package com.smm.sapp.sproject;

import android.util.Log;

import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
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
//
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

    public void PostCallWithAttachment(String URL, Map<String, String> parameter, String filePath,String key, final OkHttpCallback callback) {

        MultipartBody.Builder buildernew;
        final MediaType MEDIA_TYPE = MediaType.parse("file/*");
        if (filePath != null){
            File sourceFile = new File(filePath);
            byte[] b;
            b = new byte[(int) sourceFile.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                fileInputStream.read(b);
                for (int i = 0; i < b.length; i++) {
                    System.out.print((char) b[i]);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found.");
                e.printStackTrace();
            } catch (IOException e1) {
                System.out.println("Error Reading The File.");
                e1.printStackTrace();
            }
            buildernew = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart(key, filePath, RequestBody.create(MEDIA_TYPE, b));
        }else {
            buildernew = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
        }





        for (Map.Entry<String, String> entry : parameter.entrySet()) {
            buildernew.addFormDataPart(entry.getKey(), entry.getValue());
            Log.e(entry.getKey(),entry.getValue() + " ");
        }

        MultipartBody  body = buildernew.build();

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

    public void PostCallWithAttachment(String URL, Map<String, String> parameter,Map<String, String> attachParameter, final OkHttpCallback callback) {

        MultipartBody.Builder buildernew;
        final MediaType MEDIA_TYPE = MediaType.parse("file/*");

        buildernew = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        for (Map.Entry<String, String> entry : parameter.entrySet()) {
            buildernew.addFormDataPart(entry.getKey(), entry.getValue());
            Log.e(entry.getKey(),entry.getValue() + " ");
        }

        for (Map.Entry<String, String> entry : attachParameter.entrySet()) {
            byte[] b = byteArray(entry.getValue());
            buildernew.addFormDataPart(entry.getKey(), entry.getValue(),RequestBody.create(MEDIA_TYPE, b));
            Log.e(entry.getKey(),entry.getValue() + " ");
        }

        MultipartBody  body = buildernew.build();

        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(460, TimeUnit.SECONDS)
                .readTimeout(460, TimeUnit.SECONDS)
                .writeTimeout(460, TimeUnit.SECONDS)
                .build();

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

    private byte[] byteArray(String value) {
        File sourceFile = new File(value);
        byte[] b;
        b = new byte[(int) sourceFile.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            fileInputStream.read(b);

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Error Reading The File.");
            e1.printStackTrace();
        }

        return b;
    }

}


