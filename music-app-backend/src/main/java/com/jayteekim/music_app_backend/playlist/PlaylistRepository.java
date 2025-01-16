package com.jayteekim.music_app_backend.playlist;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaylistRepository extends MongoRepository<Playlist, String> {
    // function for finding all playlists by userId. should return an arraylist of the playlist ids
    ArrayList<String> findByUserId(String userId);
}
