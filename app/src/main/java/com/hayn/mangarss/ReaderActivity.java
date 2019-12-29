package com.hayn.mangarss;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.concurrent.ExecutionException;

public class ReaderActivity extends AppCompatActivity {

    SharedPreferences prefs;

    private WebView webReader;
    private String url = "https://onmanga.com/manga/solo-leveling/solo-leveling-chapter-5/?style=list";

    private String styleLight = "<style type=\"text/css\">\n" +
            "html {\n" +
            " background-color: #ffffff;}\n" + //background color light
            ".read-container{\n" +
            " text-align: center;}\n" +
            "img {\n" +
            " width: auto\\9;\n" +
            " max-width: 100%;\n" +
            " height: auto; } " +
            "</style>";
    private String styleDark = "<style type=\"text/css\">\n" +
            "html {\n" +
            " background-color: #6c757d;}\n" +
            ".read-container{\n" +
            " text-align: center;}\n" +
            "img {\n" +
            " width: auto\\9;\n" +
            " max-width: 100%;\n" +
            " height: auto; } " +
            "</style>";

    Boolean darkmode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        prefs = this.getSharedPreferences(this.getPackageName(),Context.MODE_PRIVATE);
        loadPrefs();

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

        String result = null;
        try {
            result = new HttpGetRequest().execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }


        String htmlReading, html;

        Document doc = Jsoup.parse(result);
        Elements readingElements = doc.select("div[class=reading-content]");
        htmlReading = readingElements.html();

        /**
        Elements headerElements = doc.select("style[type=text/css]");
        htmlCSS = headerElements.html();
        */

        //Original Combine, doesnt center or do shit, no render
        //htmlCSS = "<head><link rel=\"stylesheet\" type=\"text/css\" href=\"https://onmanga.com/wp-content/themes/madara/style.css\"></head>";

        html = "<html><head>" + styleDark + "</head><body><div class=\"read-container\"><div class=\"reading-content\">" + htmlReading + "</div></div></body></html>";



        webReader = findViewById(R.id.WebViewReader);
        //webReader.setWebViewClient(new WebViewClient());
        //webReader.loadUrl(url);      //,"text/html","UTF-8");

        webReader.getSettings().setLoadWithOverviewMode(true);
        webReader.getSettings().setUseWideViewPort(true);

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

    private void loadPrefs(){
        darkmode = prefs.getBoolean("",false);
    }
}
