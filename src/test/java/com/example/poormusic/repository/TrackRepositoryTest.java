package com.example.poormusic.repository;

import com.example.poormusic.PoorMusicApplication;
import com.example.poormusic.entity.Track;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PoorMusicApplication.class)
@Profile("test")
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;

    @Test
    public void testFindTracksByTitleContainingIgnoreCase() {
//        Page<Track> result = trackRepository.findTracksByTitleContainingIgnoreCase("Test", PageRequest.of(0, 10));
//        assertThat(result.getContent()).isNotEmpty();
//
//        result.getContent().forEach(track -> {
//            System.out.println("Track Title: " + track.getTitle());
//            System.out.println("Track Duration: " + track.getDuration());
//            track.getArtists().forEach(artist -> {
//                System.out.println("Artist: " + artist.getTitle());
//            });
//        });

        String title = "Show Luv";
        log.debug("Testing findTracksByTitleContainingIgnoreCase with title: {}", title);

        Page<Track> tracks = trackRepository.findTracksByTitleContainingIgnoreCase(title, Pageable.ofSize(2));

        log.debug("Number of tracks found: {}", tracks.getSize());
        for (Track track : tracks) {
            log.debug("Track found: {}", track);
        }

        assertFalse(tracks.isEmpty(), "Expected non-empty list of tracks");
    }
}
