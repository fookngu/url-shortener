package net.toiditimtoi.urlshortener.controller;

import net.toiditimtoi.urlshortener.model.UrlRequest;
import net.toiditimtoi.urlshortener.model.UrlResponse;
import net.toiditimtoi.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/shorten")
public class UrlShortenerController {

    private final String hostName;
    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(@Value("${app.hostname:http://localhost}") String hostName, UrlShortenerService urlShortenerService) {
        this.hostName = hostName;
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest request) {
        var urlMapping = urlShortenerService.shortenUrl(request.longUrl());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UrlResponse(hostName + "/" + urlMapping.getHashUrl()));
    }

}
