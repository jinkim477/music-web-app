package com.jayteekim.music_app_backend.spotify;

public class Album {
    private String name;
    private String id;
    private String releaseDate;
    private String imageUrl;

    public Album(String name, String id, String releaseDate, String imageUrl) {
        this.name = name;
        this.id = id;
        this.releaseDate = releaseDate;
        this.imageUrl = imageUrl;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
