package com.jayteekim.music_app_backend.user;

import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    
    @Id
    private String id;
    private String spotifyId;
    private String username;
    private String email;
    private String accessToken;
    private String refreshToken;
    private ArrayList<String> playlistIds = new ArrayList<>();
    private ArrayList<String> albumIds = new ArrayList<>();

    public User() {
    }

    public User(String spotifyId, String username, String email, String accessToken, String refreshToken) {
        this.spotifyId = spotifyId;
        this.username = username;
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public ArrayList<String> getPlaylistIds() {
        return playlistIds;
    }

    public void setPlaylistIds(ArrayList<String> playlistIds) {
        this.playlistIds = playlistIds;
    }

    public void addPlaylistId(String playlistId) {
        this.playlistIds.add(playlistId);
    }

    public void removePlaylistId(String playlistId) {
        this.playlistIds.remove(playlistId);
    }

    public ArrayList<String> getAlbumIds() {
        return albumIds;
    }

    public void setAlbumIds(ArrayList<String> albumIds) {
        this.albumIds = albumIds;
    }

    public void addAlbumId(String albumId) {
        this.albumIds.add(albumId);
    }

    public void removeAlbumId(String albumId) {
        this.albumIds.remove(albumId);
    }
}