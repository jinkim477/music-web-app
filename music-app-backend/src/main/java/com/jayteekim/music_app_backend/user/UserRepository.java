package com.jayteekim.music_app_backend.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findBySpotifyId(String spotifyId);
    User findByUsername(String username);
    User findByEmail(String email);
    User findByAccessToken(String accessToken);
    User findByRefreshToken(String refreshToken);
}
