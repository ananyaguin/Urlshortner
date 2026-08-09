package com.urlshortner.dto;

public class UrlResponse {

    private String originalUrl;

    private String shortUrl;

    public UrlResponse(String originalUrl, String shortUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}
