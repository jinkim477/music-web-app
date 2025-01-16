package com.jayteekim.music_app_backend.playlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayteekim.music_app_backend.user.User;
import com.jayteekim.music_app_backend.user.UserRepository;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserRepository userRepository;

    public Playlist createPlaylist(Playlist playlist) {
        Playlist savedPlaylist = playlistRepository.save(playlist);
        User user = userRepository.findById(playlist.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.getPlaylistIds().add(savedPlaylist.getId());
        userRepository.save(user);
        return savedPlaylist;
    }

    public void deletePlaylist(String id) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
        User user = userRepository.findById(playlist.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.getPlaylistIds().remove(playlist.getId());
        userRepository.save(user);
        playlistRepository.deleteById(id);
    }

    public Playlist updatePlaylist(String id, Playlist updatedPlaylist) {
        Playlist existingPlaylist = playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
        existingPlaylist.setName(updatedPlaylist.getName());
        existingPlaylist.setDescription(updatedPlaylist.getDescription());
        existingPlaylist.setSongUris(updatedPlaylist.getSongUris());
        return playlistRepository.save(existingPlaylist);
    }

    public List<String> getAllPlaylistIdsByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPlaylistIds();
    }
}
