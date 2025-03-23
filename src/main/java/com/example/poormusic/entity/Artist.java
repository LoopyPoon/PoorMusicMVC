package com.example.poormusic.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "artist")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Artist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @ManyToMany(mappedBy = "artists")
    private Set<Album> albums;

    @ManyToMany(mappedBy = "artists")
    private Set<Genre> genres;

    @ManyToMany(mappedBy = "artists")
    private Set<Track> tracks;
}
