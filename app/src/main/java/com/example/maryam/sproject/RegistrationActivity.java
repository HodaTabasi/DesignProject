package com.example.maryam.sproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        FragmentsUtil.addFragment(RegistrationActivity.this, R.id.register_container, new RegisterFragment());

    }
}
