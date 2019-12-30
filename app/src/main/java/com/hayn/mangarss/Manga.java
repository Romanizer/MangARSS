package com.hayn.mangarss;

public class Manga {
    String name;
    int progress;
    String url; // url = https + style=list etc..
    Boolean favorite;

    // example "https://onmanga.com/manga/solo-leveling/solo-leveling-chapter-4/?style=list";

    public Manga(String url, Boolean favorite){
        //TODO: check url
        url = url.trim();
        if(!url.contains("/?style=")) url += "/?style=list";

        if(!url.contains("https") | url.contains("http")) url = "https://" + url;

        name = url.split("onmanga.com/manga/")[1];   // part after manga/NAME OF MANGE/bullshit
        name = name.split("/")[0];

        this.url = url;
        this.progress = 1;
        this.name = url.trim();
        this.favorite = favorite;
    }

    void setFavorite(Boolean favorite){
        this.favorite = favorite;
    }

    void updateProgress(int progress){
        this.progress = progress;
    }

    Boolean isFavorite(){
        return favorite;
    }

    String getName() {
        return name;
    }

    int getProgress() {
        return progress;
    }

    String getUrl() {
        return url;
    }
}
