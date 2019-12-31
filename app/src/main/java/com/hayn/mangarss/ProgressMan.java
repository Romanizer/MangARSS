package com.hayn.mangarss;

import android.content.SharedPreferences;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.json.*;

public class ProgressMan {

    private static final String FILE_NAME = "Manga.json";

    SharedPreferences prefs;

    private Map<String,Manga> mangalist = new HashMap<>(); // maybe remove?
    private JSONArray JArray;

    private static ProgressMan Instance = new ProgressMan();

    private ProgressMan(){
        //TODO: if file not present init new file
        // implement JSON??

        //    FileUtils.something
    }

    public void addManga(Manga m) throws JSONException, IOException {
        JSONObject tmpObj = new JSONObject();
        tmpObj.put("name", m.getName());
        tmpObj.put("progress", m.getProgress());
        tmpObj.put("URL", m.getUrl());
        tmpObj.put("favorite", m.isFavorite().toString());
        JArray.put(tmpObj);

        //write to File?
        writeToFile();
    }

    private synchronized void readFile(){
        try {
            JArray = new JSONArray(FileUtils.readFileToString(new File(FILE_NAME), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void writeToFile() throws IOException {
        FileUtils.writeStringToFile(new File(FILE_NAME), JArray.toString(),"utf-8");
        //writes JSON ARRAY JArray to file
    }

    int length(){
        return JArray.length();
    }

    public ProgressMan getInstance(){
        return Instance;
    }
}
