package com.hayn.mangarss;


import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class MangaLoaderTask extends AsyncTask<String, Void, String> {

    public MangaLoaderTask(AsyncResponse delegate, int index){
        this.delegate = delegate;
        this.index = index;
    }

    private AsyncResponse delegate = null;
    private int index;

    @Override
    protected String doInBackground(String... params){
        try {
            Document doc = Jsoup.connect(params[0]).get();

            //   Elements titleElements = doc.select("div h5 a");
            Elements urlElements = doc.select("div[class=summary_image] a img");  // + " img"
            System.out.println("andrew "  + urlElements.size());

            return urlElements.attr("data-src"); //data-src

        } catch (IOException e){
            e.printStackTrace();
        }

        return "";
    }

    protected void onPostExecute(String result){
        delegate.processFinish(result, index);
    }
}