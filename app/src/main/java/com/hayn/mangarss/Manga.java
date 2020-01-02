package com.hayn.mangarss;

public class Manga {
    private String name;
    private int progress;
    private String url; // url = https + style=list etc..
    private String imgUrl;
    private Boolean favorite;

    // example "https://onmanga.com/manga/solo-leveling/solo-leveling-chapter-4/?style=list";

    public Manga(String url, String imgUrl, Boolean favorite){
        //TODO: check url
        url = url.trim();
        imgUrl = imgUrl.trim();

        if(!url.contains("/?style=")) url += "/?style=list";
        if(!url.contains("https://") | !url.contains("http://")) url = "https://" + url;

        name = url.split("onmanga.com/manga/")[1];   // part after manga/NAME OF MANGA/bullshit
        name = name.split("/")[0];

        this.url = url;
        this.progress = 1;
        this.name = url.trim();
        this.favorite = favorite;
        this.imgUrl = imgUrl;
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
