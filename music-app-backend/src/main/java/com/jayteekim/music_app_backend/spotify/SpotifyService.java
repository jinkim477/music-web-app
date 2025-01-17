package com.jayteekim.music_app_backend.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
public class SpotifyService {
    
    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    @Value("${spotify.redirect.uri}")
    private String redirectUri;

    private final String SPOTIFY_API_URL = "https://api.spotify.com/v1";

    private final RestTemplate restTemplate;

    public SpotifyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Method for searching tracks
    // public String searchTracks(String query) {
    //     String url = UriComponentsBuilder.fromUriString(SPOTIFY_API_URL + "/search")
    //             .queryParam("q", query)
    //             .queryParam("type", "track")
    //             .build()
    //             .toUriString();
    //     return restTemplate.getForObject(url, String.class);
    // }
    public List<Track> searchTracks(String query, String accessToken) {
        String url = "https://api.spotify.com/v1/search";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("q", query)
                .queryParam("type", "track");
        
        ResponseEntity<Map> response = restTemplate.exchange(
            builder.toUriString(),
            HttpMethod.GET,
            entity,
            Map.class
        );

        return parseTrackResponse(response.getBody()); // todo: implement this method or find another way to parse the response
    }

    // Method for searching albums
    public String searchAlbums(String query) {
        String url = UriComponentsBuilder.fromUriString(SPOTIFY_API_URL + "/search")
                .queryParam("q", query)
                .queryParam("type", "album")
                .build()
                .toUriString();
        return restTemplate.getForObject(url, String.class);
    }

    // Method for searching artists
    public String searchArtists(String query) {
        String url = UriComponentsBuilder.fromUriString(SPOTIFY_API_URL + "/search")
                .queryParam("q", query)
                .queryParam("type", "artist")
                .build()
                .toUriString();
        return restTemplate.getForObject(url, String.class);
    }

    // Method to get artists discography
    public String getArtistDiscography(String artistId) {
        String url = SPOTIFY_API_URL + "/artists/" + artistId + "/albums";
        return restTemplate.getForObject(url, String.class);
    }


}