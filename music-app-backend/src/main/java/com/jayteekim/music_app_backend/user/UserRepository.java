package com.jayteekim.music_app_backend.user;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findBySpotifyId(String spotifyId);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByAccessToken(String accessToken);
    Optional<User> findByRefreshToken(String refreshToken);
}
