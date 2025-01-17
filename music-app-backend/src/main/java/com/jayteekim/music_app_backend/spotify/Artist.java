package com.jayteekim.music_app_backend.spotify;

public class Artist {
    private String name;
    private String id;
    private String imageUrl;
    private String bio;

    public Artist(String name, String id, String imageUrl, String bio) {
        this.name = name;
        this.id = id;
        this.imageUrl = imageUrl;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
