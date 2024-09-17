package com.example.mymusic_backend.repositories;


import com.example.mymusic_backend.models.collections.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
