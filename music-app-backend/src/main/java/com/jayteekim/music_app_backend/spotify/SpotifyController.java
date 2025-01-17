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

    private final SpotifyService spotifyService;

    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    // Endpoint for searching tracks
    @GetMapping("/api/spotify/search/tracks")
    public ResponseEntity<?> searchTracks(@RequestParam String query) {
        return ResponseEntity.ok(spotifyService.searchTracks(query));
    }

    // Endpoint for searching albums
    @GetMapping("/api/spotify/search/albums")
    public ResponseEntity<?> searchAlbums(@RequestParam String query) {
        return ResponseEntity.ok(spotifyService.searchAlbums(query));
    }

    // Endpoint for searching artists
    @GetMapping("/api/spotify/search/artists")
    public ResponseEntity<?> searchArtists(@RequestParam String query) {
        return ResponseEntity.ok(spotifyService.searchArtists(query));
    }

    // Endpoint for getting artist discography (albums)
    @GetMapping("/api/spotify/artists/discography")
    public ResponseEntity<?> getArtistDiscography(@RequestParam String artistId) {
        return ResponseEntity.ok(spotifyService.getArtistDiscography(artistId));
    }
    
}