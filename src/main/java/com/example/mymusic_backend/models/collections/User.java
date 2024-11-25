package com.example.mymusic_backend.models.collections;

import com.example.mymusic_backend.models.Artist;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    private String password;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Playlist> playlists = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Artist> artists = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Music> musics = new HashSet<>();



    private Long popularity;

    @PrePersist
    private void prePersist(){
        this.popularity = 0L;
    }

    public void addPlaylist(Playlist playlist){
        this.playlists.add(playlist);
    }

    public void removePlaylist(Playlist playlist){
        this.playlists.remove(playlist);
    }

    public void addArtist(Artist artist){
        this.artists.add(artist);
    }

    public void removeArtist(Artist artist){
        this.artists.remove(artist);
    }

    public void addMusic(Music music){
        this.musics.add(music);
    }
    public void removeMusic(Music music){
        this.musics.remove(music);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getName(), user.getName()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getPlaylists(), user.getPlaylists()) && Objects.equals(getPopularity(), user.getPopularity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getName(), getPassword(), getPopularity());
    }
}
