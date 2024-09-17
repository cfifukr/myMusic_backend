package com.example.mymusic_backend.models.collections;


import com.example.mymusic_backend.models.Image;
import com.example.mymusic_backend.models.Music;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Music> musicSet = new HashSet<>();

    @ManyToOne
    private User user;

    @ManyToOne
    private Image logoLink;

    private Long popularity;

    public void addMusic(Music music){
        this.musicSet.add(music);
    }

    public void removeMusic(Music music){
        this.musicSet.remove(music);
    }

    @PrePersist
    private void prePersist(){
        this.popularity = 0L;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist playlist)) return false;
        return Objects.equals(getId(), playlist.getId()) && Objects.equals(getName(), playlist.getName()) && Objects.equals(getMusicSet(), playlist.getMusicSet()) && Objects.equals(getUser(), playlist.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(),  getUser());
    }
}
