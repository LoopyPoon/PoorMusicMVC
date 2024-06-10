package com.example.poormusic.service;

import com.example.poormusic.entity.Playlist;
import com.example.poormusic.repository.PlaylistRepository;
import com.example.poormusic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository, UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void savePlaylist(Playlist playlist) throws IOException {

    }
}
