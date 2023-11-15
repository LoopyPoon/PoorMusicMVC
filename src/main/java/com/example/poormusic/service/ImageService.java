package com.example.poormusic.service;

import com.example.poormusic.entity.Image;
import com.example.poormusic.entity.Playlist;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface ImageService {

    // Загрузить новое изображение
    Image addImage(MultipartFile file, Playlist playlist) throws IOException;

    // Найти изображение по его ID
    Image findImageById(Long imageId);

    // Скачать файл
    Resource loadFileAsResource(String uploadYear, String fileName) throws MalformedURLException;
}
