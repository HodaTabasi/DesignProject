package com.smm.sapp.sproject.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.HelperClass.SharedPreferencesApp;
import com.smm.sapp.sproject.Models.NotificationsModels;
import com.smm.sapp.sproject.Models.User;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 10000;
    Intent mainIntent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "JFFlatregular.ttf", true);

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        ImageView logo = findViewById(R.id.splash);

        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        Animation blink = AnimationUtils.loadAnimation(this, R.anim.blink);

        logo.startAnimation(bounce);
        tv1.startAnimation(blink);
        tv2.startAnimation(blink);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (SharedPreferencesApp.getInstance(SplashActivity.this).getStringData("MyObject").equals("")) {
                    mainIntent = new Intent(SplashActivity.this, FirstActivity.class);
                    startActivity(mainIntent);
                    finish();
                } else {
                    Gson gson = new Gson();
                    String json = SharedPreferencesApp.getInstance(SplashActivity.this).getStringData("MyObject");
                    ConstantInterFace.USER = gson.fromJson(json, User.class);
                    getNotifications(ConstantInterFace.USER.getToken());
                }


            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void getNotifications(String token) {
        MyRequest myRequest = new MyRequest();
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/mynotifications?token="+token, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SplashActivity.this, "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                String s = response.body().string();
                Log.e("okHttpClient",""+s);

                final JSONObject jsonObject = new JSONObject(s);
                final JSONObject object = jsonObject.getJSONObject("status");
                final Gson gson = new Gson();
               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                ConstantInterFace.notificationsModels = gson.fromJson(jsonObject.getJSONArray("notifications").toString(), new TypeToken<ArrayList<NotificationsModels>>(){}.getType());
                                for (NotificationsModels models :ConstantInterFace.notificationsModels){
                                    if (models.getSeen().equals("0"))
                                        ConstantInterFace.NOTIFICATION_NUMBER ++;
                                }
                                mainIntent = new Intent(SplashActivity.this, ContainerActivity.class);
                                startActivity(mainIntent);
                                finish();
                            } else {
                                Toast.makeText(SplashActivity.this, "لم يتم الارسال بشكل صحيح", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

    }


    @Override
    public void onBackPressed() {
        finish();
    }


}
