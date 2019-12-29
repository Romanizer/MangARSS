package com.hayn.mangarss;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ReaderActivity extends AppCompatActivity {

    private WebView webReader;
    private String url = "https://onmanga.com/manga/solo-leveling/solo-leveling-chapter-1/?style=list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        /**
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setCollapsible(true); // what dis
        */

        webReader = findViewById(R.id.WebViewReader);
        webReader.setWebViewClient(new WebViewClient());
        webReader.loadUrl(url);      //,"text/html","UTF-8");


        WebSettings webSettings = webReader.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webReader.setBackgroundColor(1);

        /**
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         */




    }

}
