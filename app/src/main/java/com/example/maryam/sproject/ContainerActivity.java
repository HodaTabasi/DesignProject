package com.example.maryam.sproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        FragmentsUtil.addFragment(ContainerActivity.this, R.id.container_activity, new MainFragment(),false);
    }
}
