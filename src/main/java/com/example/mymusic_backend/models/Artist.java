package com.example.mymusic_backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Album> albums = new HashSet<>();

    @OneToOne
    private Image logo;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;
        return Objects.equals(getId(), artist.getId()) && Objects.equals(getName(), artist.getName()) && Objects.equals(getAlbums(), artist.getAlbums()) && Objects.equals(getLogo(), artist.getLogo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAlbums(), getLogo());
    }
}
