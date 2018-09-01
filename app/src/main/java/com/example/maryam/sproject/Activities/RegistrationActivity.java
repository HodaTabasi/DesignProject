package com.example.maryam.sproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.maryam.sproject.Fragments.RegisterFragment;
import com.example.maryam.sproject.HelperClass.FragmentsUtil;
import com.example.maryam.sproject.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        FragmentsUtil.addFragment(RegistrationActivity.this, R.id.register_container, new RegisterFragment());

    }
}
