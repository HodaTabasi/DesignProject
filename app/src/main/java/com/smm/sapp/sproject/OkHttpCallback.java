package com.smm.sapp.sproject;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public interface OkHttpCallback {

    void onFailure(Call call, IOException e);

    void onResponse(Call call, Response response) throws IOException, JSONException;
}
