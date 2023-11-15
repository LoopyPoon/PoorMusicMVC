package com.example.poormusic.service;

import com.example.poormusic.entity.Playlist;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PlaylistService {

    void savePlaylist(Playlist playlist) throws IOException;
}
