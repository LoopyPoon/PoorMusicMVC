package com.example.poormusic.repository;

import com.example.poormusic.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByTitle(String title);
    @EntityGraph(attributePaths = "genres")
    @Query("SELECT a FROM Artist a LEFT JOIN FETCH a.genres WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Artist> findArtistsByTitleContainingIgnoreCase(@Param("query") String query, Pageable pageable);
}
