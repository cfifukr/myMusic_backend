package com.example.mymusic_backend.models.collections;

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

    @OneToMany
    private Set<Playlist> playlists = new HashSet<>();


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
