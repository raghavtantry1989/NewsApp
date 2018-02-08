package com.southkart.newsapp;

/**
 * Created by tantryr on 2/5/18.
 */

public class News {
    private String webTitle;
    private String webUrl;
    private String webPublicationDate;
    private String sectionName;

    public News(String sectionName, String webPublicationDate, String webTitle, String webUrl ) {
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.webPublicationDate = webPublicationDate;
        this.sectionName = sectionName;
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
}
