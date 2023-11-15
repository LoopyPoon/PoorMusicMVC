package com.example.poormusic.service;

import com.example.poormusic.entity.Image;
import com.example.poormusic.entity.Playlist;
import com.example.poormusic.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image addImage(MultipartFile file, Playlist playlist) throws IOException {
        return null;
    }

    @Override
    public Image findImageById(Long imageId) {
        return null;
    }

    @Override
    public Resource loadFileAsResource(String uploadYear, String fileName) throws MalformedURLException {
        return null;
    }
}
