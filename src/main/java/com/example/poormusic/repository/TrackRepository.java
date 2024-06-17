package com.example.poormusic.repository;

import com.example.poormusic.entity.Track;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    @NotNull
    List<Track> findAll();
    Set<Track> findAllByPlaylistsId(Long PlaylistId);

}
