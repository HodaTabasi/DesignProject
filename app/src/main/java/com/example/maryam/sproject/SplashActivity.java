package com.example.maryam.sproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView reg_img = findViewById(R.id.img_reg);
        ImageView skip_img = findViewById(R.id.img_skip);

        reg_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        skip_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this, ContainerActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
