package com.southkart.newsapp;

/**
 * Created by tantryr on 2/5/18.
 */

public class News {
    private String webTitle;
    private String webUrl;
    private String apiUrl;
    private String webPublicationDate;
    private String sectionName;

    public News(String webTitle, String webUrl, String apiUrl, String webPublicationDate) {
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.apiUrl = apiUrl;
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }
}
