package com.urlshortner.service;

import com.urlshortner.dto.UrlRequest;
import com.urlshortner.dto.UrlResponse;
import com.urlshortner.entity.UrlMapping;
import com.urlshortner.repository.UrlRepository;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    private final Random random = new Random();

    private static final String CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    // CREATE SHORT URL
    public UrlResponse createShortUrl(UrlRequest request) {

        if (request == null ||
                request.getUrl() == null ||
                request.getUrl().trim().isEmpty()) {

            throw new RuntimeException("URL cannot be empty");
        }

        String originalUrl = request.getUrl().trim();

        validateUrl(originalUrl);

        String shortCode;

        // CUSTOM SHORT WORD
        if (request.getCustomWord() != null &&
                !request.getCustomWord().trim().isEmpty()) {

            shortCode = request.getCustomWord()
                    .trim()
                    .toLowerCase()
                    .replaceAll("\\s+", "-");

            if (!shortCode.matches("[a-z0-9-]+")) {
                throw new RuntimeException(
                        "Custom word can contain only letters, numbers and hyphens"
                );
            }

            if (urlRepository.existsByShortCode(shortCode)) {
                throw new RuntimeException(
                        "This custom short word is already taken"
                );
            }

        } else {

            // RANDOM SHORT CODE
            do {
                shortCode = generateShortCode(6);
            } while (urlRepository.existsByShortCode(shortCode));
        }

        UrlMapping urlMapping =
                new UrlMapping(originalUrl, shortCode);

        urlRepository.save(urlMapping);

        String shortUrl =
                "http://localhost:8080/" + shortCode;

        return new UrlResponse(
                originalUrl,
                shortUrl
        );
    }

    // FIND ORIGINAL URL
    public String getOriginalUrl(String shortCode) {

        UrlMapping urlMapping =
                urlRepository.findByShortCode(shortCode)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Short URL not found"
                                ));

        return urlMapping.getOriginalUrl();
    }

    // RECENT URLS
    public List<UrlMapping> getRecentUrls() {

        return urlRepository
                .findTop10ByOrderByCreatedAtDesc();
    }

    // GENERATE RANDOM CODE
    private String generateShortCode(int length) {

        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {

            int index =
                    random.nextInt(CHARACTERS.length());

            code.append(CHARACTERS.charAt(index));
        }

        return code.toString();
    }

    // URL VALIDATION
    private void validateUrl(String url) {

        try {

            URI uri = URI.create(url);

            if (uri.getScheme() == null ||
                    (!uri.getScheme().equalsIgnoreCase("http")
                            && !uri.getScheme().equalsIgnoreCase("https"))) {

                throw new RuntimeException(
                        "URL must start with http:// or https://"
                );
            }

            if (uri.getHost() == null) {

                throw new RuntimeException(
                        "Please enter a valid URL"
                );
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Please enter a valid URL"
            );
        }
    }
}



