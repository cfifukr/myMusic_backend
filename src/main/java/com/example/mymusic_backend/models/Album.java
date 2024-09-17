package com.example.mymusic_backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Music> musics = new HashSet<>();

    private Integer likes;

    private Integer dislikes;

    @OneToOne
    private Image logo;




    public void addMusic(Music music){
        music.setAlbum(this);
        this.musics.add(music);
    }

    public void removeMusic(Music music){
        this.musics.remove(music);

    }
}
