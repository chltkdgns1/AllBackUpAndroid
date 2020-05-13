package com.gkftndlTek.todaymovie;

import java.io.Serializable;

public class MovieData implements Serializable {

    private String title;
    private String urlimage;
    private String subtitle;
    private String pubDate;
    private String director;
    private String actor;
    private String UserRationg;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrlimage() {
        return urlimage;
    }
    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public String getPubDate() {
        return pubDate;
    }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getActor() {
        return actor;
    }
    public void setActor(String actor) {
        this.actor = actor;
    }
    public String getUserRationg() {
        return UserRationg;
    }
    public void setUserRationg(String userRationg) {
        UserRationg = userRationg;
    }





}
