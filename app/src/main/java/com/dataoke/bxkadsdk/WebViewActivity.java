package com.dataoke.bxkadsdk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {
    private WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_webview_layout);
        webview=findViewById(R.id.webview);
        String url = getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(url)){
            Toast.makeText(this,"url为空，打开失败",Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url);
    }
}
