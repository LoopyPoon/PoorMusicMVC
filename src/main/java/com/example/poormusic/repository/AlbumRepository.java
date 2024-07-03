package com.example.poormusic.repository;

import com.example.poormusic.entity.Album;
import com.example.poormusic.entity.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAllByUsersId(Long userId);
    Optional<Album> findByTitle(String title);
    Page<Album> findAlbumsByTitleContainingIgnoreCase(@Param("query") String query, Pageable pageable);
}
