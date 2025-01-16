package com.jayteekim.music_app_backend.auth;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class AuthController {

    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    @Value("${spotify.redirect.uri}")
    private String redirectUri;

    private final String SPOTIFY_ACCOUNTS_URL = "https://accounts.spotify.com";

    @GetMapping("/api/auth/login")
    public ResponseEntity<?> login() {
        System.out.println("Configured Redirect URI: " + redirectUri); // Debug log for redirect URI

        URI spotifyAuthUrl = UriComponentsBuilder
                .fromUriString(SPOTIFY_ACCOUNTS_URL + "/authorize")
                .queryParam("client_id", clientId)
                .queryParam("response_type", "code")
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", "user-read-private user-read-email playlist-modify-public")
                .build()
                .toUri();

        // debug statement
        System.out.println("Generated Spotify Authorization URL: " + spotifyAuthUrl);

        return ResponseEntity.status(HttpStatus.FOUND).location(spotifyAuthUrl).build();
    }

    @GetMapping("/api/auth/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code) {
        // Exchange authorization code for access and refresh tokens
        RestTemplate restTemplate = new RestTemplate();

        String tokenUrl = SPOTIFY_ACCOUNTS_URL + "/api/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", code);
        body.add("redirect_uri", redirectUri);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(response.getBody());
        }

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
