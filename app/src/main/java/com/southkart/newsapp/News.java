package com.southkart.newsapp;

/**
 * Created by tantryr on 2/5/18.
 */

public class News {
    private String webTitle;
    private String webUrl;
    private String webPublicationDate;
    private String sectionName;
    private String author;

    public News(String sectionName, String webPublicationDate, String webTitle, String webUrl, String author ) {
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.webPublicationDate = webPublicationDate;
        this.sectionName = sectionName;
        this.author = author;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getAuthor() {
        return author;
    }
}
