package com.hayn.mangarss;


import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.util.HashMap;


public class HomeLoaderTask extends AsyncTask<String, Void, HashMap<String,String>> {

    public HomeLoaderTask(AsyncResponseArray delegate){
        this.delegate = delegate;
    }

    private AsyncResponseArray delegate = null;

    @Override
    protected HashMap<String,String> doInBackground(String... params){

        HashMap<String,String> result = new HashMap<>();
        Document doc;

        try {
            doc = Jsoup.connect(params[0]).get();

            Elements titleElements = doc.select("div h5 a");
            Elements urlElements = doc.select("div[class=item-thumb hover-details c-image-hover] a");  // + " img"
            System.out.println("andrewjjjj " + titleElements.size() + "    " + urlElements.size());

            for (int i = 0; i < titleElements.size(); i++) {
                result.put(titleElements.get(i).text(), urlElements.get(i).attr("href")); //data-src
            }
        } catch (IOException e){
            e.printStackTrace();
        }


        return result;
    }
    protected void onPostExecute(HashMap<String,String> result){
        delegate.processFinish(result);
    }
}