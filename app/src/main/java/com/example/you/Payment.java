package com.example.you;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.you.databinding.ActivityPaymentBinding;

public class Payment extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityPaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        WebView webview = (WebView)findViewById(R.id.web_view01);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("https://payment-form.glitch.me/");

    }


}