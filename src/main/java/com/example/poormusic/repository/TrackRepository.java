package com.example.poormusic.repository;

import com.example.poormusic.entity.Track;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    @NotNull
    List<Track> findAll();
    Set<Track> findAllByPlaylistsId(Long playlistId);
    Set<Track> findAllByAlbumId(Long albumId);
//    Page<Track> findTracksByTitleContainingIgnoreCase(String name, Pageable pageable);
    @EntityGraph(attributePaths = {"artists", "album"})
    @Query("SELECT t FROM Track t LEFT JOIN FETCH t.artists WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Track> findTracksByTitleContainingIgnoreCase(@Param("query") String query, Pageable pageable);

}
