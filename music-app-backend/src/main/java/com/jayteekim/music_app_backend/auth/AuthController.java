package com.jayteekim.music_app_backend.auth;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

import com.jayteekim.music_app_backend.user.User;
import com.jayteekim.music_app_backend.user.UserRepository;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    @Value("${spotify.redirect.uri}")
    private String redirectUri;

    private final String SPOTIFY_ACCOUNTS_URL = "https://accounts.spotify.com";

    @GetMapping("/api/auth/login")
    public ResponseEntity<?> login() {
        URI spotifyAuthUrl = UriComponentsBuilder
                .fromUriString(SPOTIFY_ACCOUNTS_URL + "/authorize")
                .queryParam("client_id", clientId)
                .queryParam("response_type", "code")
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", "user-read-private user-read-email playlist-modify-public")
                .build()
                .toUri();

        return ResponseEntity.status(HttpStatus.FOUND).location(spotifyAuthUrl).build();
    }

    @GetMapping("/api/auth/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code) {
        RestTemplate restTemplate = new RestTemplate();

        // Exchange authorization code for tokens
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
            Map<String, Object> responseBody = response.getBody();
            String accessToken = (String) responseBody.get("access_token");
            String refreshToken = (String) responseBody.get("refresh_token");
            Integer expiresIn = (Integer) responseBody.get("expires_in");

            // Get the user's email from Spotify
            String userEmail = getUserEmailFromSpotify(accessToken);

            // Find or create the user in the database
            User user = userRepository.findByEmail(userEmail).orElse(new User());
            user.setEmail(userEmail);
            user.setAccessToken(accessToken);
            user.setRefreshToken(refreshToken);
            user.setTokenExpiry(System.currentTimeMillis() + expiresIn * 1000L);

            userRepository.save(user);

            URI frontendHomeUri = UriComponentsBuilder.fromUriString("http://localhost:3000/home")
                .queryParam("userId", user.getId())
                .build()
                .toUri();

            // Return userId as the response
            return ResponseEntity.status(HttpStatus.FOUND).location(frontendHomeUri).build();
        }

        // Handle error
        return ResponseEntity.status(response.getStatusCode())
                .body(Map.of("error", "Failed to exchange code for tokens"));
    }

    private String getUserEmailFromSpotify(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.spotify.com/v1/me";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("email")) {
                return (String) responseBody.get("email");
            } else {
                throw new RuntimeException("Email not found in Spotify response");
            }
        }
        throw new RuntimeException("Failed to fetch user profile: " + response.getStatusCode());
    }
}