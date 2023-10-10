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
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "artist_album",
        joinColumns = {@JoinColumn(name = "album_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "artist_id", referencedColumnName = "id")}
    )
    private List<Artist> artists = new ArrayList<>();

    @Column(name = "year", nullable = false)
    private int year;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "album")
    private List<Track> tracks = new ArrayList<>();
}
