package com.example.third;

import java.io.Serializable;

public class Newsdata implements Serializable {
    private String title, urlimage, content;

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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
