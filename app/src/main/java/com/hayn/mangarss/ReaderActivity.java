package com.hayn.mangarss;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Base64;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.concurrent.ExecutionException;

import javax.xml.transform.TransformerException;

import static com.hayn.mangarss.XPathParser.nodeListToString;

public class ReaderActivity extends AppCompatActivity {

    private WebView webReader;
    private String url = "https://onmanga.com/manga/solo-leveling/solo-leveling-chapter-5/?style=list";

    private String url2 = "https://www.arrow.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        /**
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setCollapsible(true); // what dis
        */

        String result = null;
        try {
            result = new HttpGetRequest().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String html = null;
        try {
            html = nodeListToString(new XPathParser(result).getResult("//div[@class='reading-content']/"));
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        webReader = findViewById(R.id.WebViewReader);
        webReader.setWebViewClient(new WebViewClient());
        //webReader.loadUrl(url);      //,"text/html","UTF-8");
        System.out.println("AAAAAAAA" + html);
        webReader.loadDataWithBaseURL(null, html,"text/html","UTF-8",null);

        WebSettings webSettings = webReader.getSettings();
        webSettings.setJavaScriptEnabled(false);



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
