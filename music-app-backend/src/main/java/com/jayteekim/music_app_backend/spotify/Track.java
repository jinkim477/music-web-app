package com.jayteekim.music_app_backend.spotify;

public class Track {
    private String name;
    private String id;
    private String previewUrl;
    private String albumName;
    private String artistName;
    private String albumImageUrl;

    public Track(String name, String id, String previewUrl, String albumName, String artistName, String albumImageUrl) {
        this.name = name;
        this.id = id;
        this.previewUrl = previewUrl;
        this.albumName = albumName;
        this.artistName = artistName;
        this.albumImageUrl = albumImageUrl;
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

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumImageUrl() {
        return albumImageUrl;
    }

    public void setAlbumImageUrl(String albumImageUrl) {
        this.albumImageUrl = albumImageUrl;
    }
}
