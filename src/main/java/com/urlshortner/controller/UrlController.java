package com.urlshortner.controller;

import com.urlshortner.dto.UrlRequest;
import com.urlshortner.dto.UrlResponse;
import com.urlshortner.entity.UrlMapping;
import com.urlshortner.service.UrlService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(
        origins = {
                "http://localhost:5173",
                "https://shortly-frontend-o5t6ljlsn-ananyaguins-projects.vercel.app"
        }
)
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    // CREATE SHORT URL
    @PostMapping("/api/urls/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(
            @RequestBody UrlRequest request) {

        UrlResponse response =
                urlService.createShortUrl(request);

        return ResponseEntity.ok(response);
    }

    // RECENT URLS
    @GetMapping("/api/urls/recent")
    public ResponseEntity<List<UrlMapping>> getRecentUrls() {

        return ResponseEntity.ok(
                urlService.getRecentUrls()
        );
    }

    // REDIRECT SHORT URL
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode) {

        String originalUrl =
                urlService.getOriginalUrl(shortCode);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(
                URI.create(originalUrl)
        );

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .headers(headers)
                .build();
    }
}