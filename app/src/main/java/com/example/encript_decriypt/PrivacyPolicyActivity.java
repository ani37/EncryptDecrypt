package com.example.encript_decriypt;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class PrivacyPolicyActivity extends AppCompatActivity {
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        web = findViewById(R.id.webView);
        web.loadUrl("https://www.termsfeed.com/privacy-policy/b522af69f0d4c5258675ce98c5ac1731");
    }

}
