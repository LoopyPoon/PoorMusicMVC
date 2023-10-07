package com.example.poormusic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "artist_genre",
            joinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id", referencedColumnName = "id")}
    )
    private List<Artist> artists = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "genre")
    private List<Album> albums = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "playlist_genre",
            joinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "playlist_id", referencedColumnName = "id")}
    )
    private List<Playlist> playlists = new ArrayList<>();
}
