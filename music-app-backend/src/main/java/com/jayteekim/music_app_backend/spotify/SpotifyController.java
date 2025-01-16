package com.jayteekim.music_app_backend.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jayteekim.music_app_backend.user.User;
import com.jayteekim.music_app_backend.user.UserRepository;

@RestController
public class SpotifyController {

    private static final String SPOTIFY_API_URL = "https://api.spotify.com/v1";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/api/user")
    public ResponseEntity<String> getUserInfo(@RequestParam String userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Use the access token to make a request to Spotify API
        String accessToken = user.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.spotify.com/v1/me",
                HttpMethod.GET,
                entity,
                String.class
        );

        // Parse response and return
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/api/search")
    public ResponseEntity<String> search(@RequestParam String query, @RequestParam String type) {
        String accessToken = "your_access_token"; // Replace with actual access token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = SPOTIFY_API_URL + "/search?q=" + query + "&type=" + type;
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/api/artist/discography")
    public ResponseEntity<String> getArtistsDiscography(@RequestParam String artistId) {
        String accessToken = "your token";// Retrieve stored access token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = SPOTIFY_API_URL + "/artists/" + artistId + "/albums";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}