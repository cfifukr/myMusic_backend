package com.example.mymusic_backend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Album album;

    @ManyToOne
    private Image logo;

    @Enumerated(EnumType.STRING)
    private Genre genre;


    private Long timesListened = 0L ;

    private Integer secondsDuration;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Music music)) return false;
        return Objects.equals(getId(), music.getId()) && Objects.equals(getName(), music.getName()) && Objects.equals(getArtist(), music.getArtist()) && Objects.equals(getAlbum(), music.getAlbum()) && Objects.equals(getLogo(), music.getLogo()) && getGenre() == music.getGenre() && Objects.equals(getTimesListened(), music.getTimesListened()) && Objects.equals(getSecondsDuration(), music.getSecondsDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getArtist(), getAlbum(), getLogo(), getGenre(), getTimesListened(), getSecondsDuration());
    }
}
