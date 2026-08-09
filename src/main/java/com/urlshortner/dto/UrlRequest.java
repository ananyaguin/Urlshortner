package com.urlshortner.dto;

public class UrlRequest {

    private String url;

    private String customWord;

    public UrlRequest() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCustomWord() {
        return customWord;
    }

    public void setCustomWord(String customWord) {
        this.customWord = customWord;
    }
}
