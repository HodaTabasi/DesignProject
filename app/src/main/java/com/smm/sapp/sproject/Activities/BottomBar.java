package com.smm.sapp.sproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.smm.sapp.sproject.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class BottomBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "JFFlatregular.ttf", true);


    }
}
