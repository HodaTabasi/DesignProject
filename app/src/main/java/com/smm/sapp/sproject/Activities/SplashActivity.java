package com.smm.sapp.sproject.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.smm.sapp.sproject.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 5000;

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

                Intent mainIntent = new Intent(SplashActivity.this, FirstActivity.class);
                startActivity(mainIntent);
                finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    @Override
    public void onBackPressed() {
        finish();
    }


}
