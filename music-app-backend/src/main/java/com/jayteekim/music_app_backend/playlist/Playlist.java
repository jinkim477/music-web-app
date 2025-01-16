package com.jayteekim.music_app_backend.playlist;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "playlists")
public class Playlist {
    
    @Id
    private String id;
    private String userId;
    private String name;
    private String description;
    private ArrayList<String> songUris = new ArrayList<>();

    public Playlist() {
    }

    public Playlist(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getSongUris() {
        return songUris;
    }

    public void setSongUris(ArrayList<String> songUris) {
        this.songUris = songUris;
    }

    public void addSongUri(String songUri) {
        this.songUris.add(songUri);
    }

    public void removeSongUri(String songUri) {
        this.songUris.remove(songUri);
    }
}
