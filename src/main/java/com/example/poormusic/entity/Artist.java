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
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(mappedBy = "artists")
    private List<Album> albums = new ArrayList<>();

    @ManyToMany(mappedBy = "artists")
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(mappedBy = "artists")
    private List<Track> tracks = new ArrayList<>();
}
