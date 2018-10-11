package com.smm.sapp.sproject.Activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smm.sapp.sproject.Fragments.RegisterFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class RegistrationActivity extends AppCompatActivity {
    ImageView ic_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ic_back = findViewById(R.id.ic_back);

        FragmentsUtil.addFragment(RegistrationActivity.this, R.id.register_container, new RegisterFragment(), false);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "JFFlatregular.ttf", true);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
