package com.hayn.mangarss;


import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ReaderActivity extends AppCompatActivity implements AsyncResponse {

    SharedPreferences prefs;

    private WebView webReader;

    public String navUrl = "https://onmanga.com/manga/solo-leveling/solo-leveling-chapter-4/?style=list";

    int currChapter = 4;

    private final String prefKeyDarkMode = "DarkMode";

    private final String styleLight = "<style type=\"text/css\">\n" +
            "html {\n" +
            " background-color: #ffffff;}\n" + //background color light
            ".read-container{\n" +
            " text-align: center;}\n" +
            "img {\n" +
            " width: auto\\9;\n" +
            " max-width: 100%;\n" +
            " height: auto; } " +
            "</style>";
    private final String styleDark = "<style type=\"text/css\">\n" +
            "html {\n" +
            " background-color: #545a60;}\n" +
            ".read-container{\n" +
            " text-align: center;}\n" +
            "img {\n" +
            " width: auto\\9;\n" +
            " max-width: 100%;\n" +
            " height: auto; } " +
            "</style>";

    private final String meta = "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        prefs = this.getSharedPreferences(this.getPackageName(),Context.MODE_PRIVATE);

        hideSystemUI();

        webReader = findViewById(R.id.WebViewReader);
        webReader.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url) {
                view.scrollTo(0,0);
                System.out.println("nigga be srolling");
            }
        });
        //webReader.loadUrl(navUrl);      //,"text/html","UTF-8");

        webReader.getSettings().setLoadWithOverviewMode(true);
        webReader.getSettings().setUseWideViewPort(true);

        WebSettings webSettings = webReader.getSettings();
        webSettings.setJavaScriptEnabled(false);

        webReader.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getBaseContext(),"Loading Chapter " + (currChapter + 1),Toast.LENGTH_LONG).show();

                navUrl = navUrl.replace(Integer.toString(currChapter),Integer.toString(currChapter + 1));
                currChapter++;
                System.out.println("Loading " + navUrl);
                initNewHtml(navUrl);

                return true;
            }
        });

        initNewHtml(navUrl);
    }

    @Override
    public void onResume(){
        super.onResume();
        hideSystemUI();
    }

    private void hideSystemUI(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hideSystemUI and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void initNewHtml(String navUrl){
        new HttpGetRequest(this).execute(navUrl);
    }

    @Override
    public void processFinish(String output){
        updateWebView(compileHtml(output));
    }

    @Override
    public void processFinish(String s, int i){
        // do nothing
    }

    private String compileHtml(String inputStr){

        Document doc = Jsoup.parse(inputStr);
        Elements readingElements = doc.select("div[class=reading-content]");

        //Elements headerElements = doc.select("style[type=text/css]");

        //Original Combine with sites css, doesn't center or do shit, no render
        //htmlCSS = "<head><link rel=\"stylesheet\" type=\"text/css\" href=\"https://onmanga.com/wp-content/themes/madara/style.css\"></head>";

        if(prefs.getBoolean(prefKeyDarkMode,false)){
            return "<html><head>" + meta + styleDark  + "</head><body><div class=\"read-container\"><div class=\"reading-content\">" + readingElements.html() + "</div></div></body></html>";
        }else{
            return "<html><head>" + meta + styleLight + "</head><body><div class=\"read-container\"><div class=\"reading-content\">" + readingElements.html() + "</div></div></body></html>";
        }
    }

    private void updateWebView(String html){
        System.out.println("DEBUG: " + navUrl);
        webReader.loadDataWithBaseURL(null, html,"text/html","UTF-8",null);
    }
}
