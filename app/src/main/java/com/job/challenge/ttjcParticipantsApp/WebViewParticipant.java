package com.job.challenge.ttjcParticipantsApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class WebViewParticipant extends AppCompatActivity {

    private WebView webView;
    private String urlx = "";
    private ProgressBar progressBar;
    private EditText mWebAddressBar;
    private ImageView mBtnBack;
    private ImageView mBtnClear;
    int participantsWeb = R.style.AppThemeForParticipants;
    int treeWeb = R.style.AppThemeForLearningTree;
    int participantsDetailsWeb = R.style.AppThemeForParticipantAnalysis;
    int policyWeb = R.style.AppThemeForPolicy;
    int aboutWeb = R.style.AppThemeForAbout;
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //To set theme
        Intent i = getIntent();
        counter = i.getIntExtra("counter",1);
        switch (counter) {
            case 1:
                setTheme(participantsWeb);
                break;
            case 2:
                setTheme(treeWeb);
                break;
            case 3:
                setTheme(participantsDetailsWeb);
                break;
            case 4:
                setTheme(policyWeb);
                break;
            case 5:
                setTheme(aboutWeb);
                break;
        }
        setContentView(R.layout.activity_web_view2);

        urlx = i.getStringExtra("url");
        progressBar = findViewById(R.id.progress_horizontalx);
        webView = findViewById(R.id.webView);
        mWebAddressBar = findViewById(R.id.webAddressBar);
        mBtnBack = findViewById(R.id.btnBack);
        mBtnClear = findViewById(R.id.btnClear);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(urlx);

        mWebAddressBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    webView.loadUrl("https://www.google.com/search?q="+mWebAddressBar.getText().toString());
                    return true;
                }else{
                    return false;
                }

            }
        });


        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed2();
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebAddressBar.setText("");
            }
        });
    }
    class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
            mWebAddressBar.setText(url);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
        { webView.goBack(); }else
        { super.onBackPressed(); }

    }

    private void onBackPressed2(){
        super.onBackPressed();
    }
}
