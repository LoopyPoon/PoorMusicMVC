package com.example.poormusic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "extension")
    private String extension;

    @Column(name = "link")
    private String downloadLink;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Playlist playlist;

}
