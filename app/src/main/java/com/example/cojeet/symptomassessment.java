package com.example.cojeet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class symptomassessment extends AppCompatActivity {
        private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptomassessment);
        webView=findViewById(R.id.assessment);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/symptomsassess.html");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(webView!=null){
            webView.destroy();
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Menu.class));
    }
}
