package com.example.poormusic.repository;

import com.example.poormusic.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAllByUsersId(Long userId);
    Optional<Album> findByTitle(String title);
}
