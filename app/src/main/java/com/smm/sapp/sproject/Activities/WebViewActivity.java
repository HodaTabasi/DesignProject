package com.smm.sapp.sproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.smm.sapp.sproject.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView browser = findViewById(R.id.webview);
        String url = getIntent().getStringExtra("url");
        browser.loadUrl(url);

    }
}
