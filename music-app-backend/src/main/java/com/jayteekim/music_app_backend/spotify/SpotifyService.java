package com.jayteekim.music_app_backend.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SpotifyService {

    private static final String REFRESH_URL = "https://accounts.spotify.com/api/token";
    private static final String CLIENT_ID = "your_client_id"; // Replace with your actual client ID
    private static final String CLIENT_SECRET = "your_client_secret"; // Replace with your actual client secret

    @Autowired
    private RestTemplate restTemplate;

    public String refreshAccessToken(String refreshToken) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("grant_type", "refresh_token");
        requestParams.add("refresh_token", refreshToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestParams, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(REFRESH_URL, requestEntity, Map.class);

        String newAccessToken = response.getBody().get("access_token").toString();
        return newAccessToken;
    }
}