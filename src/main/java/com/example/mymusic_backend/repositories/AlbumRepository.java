package com.example.mymusic_backend.repositories;

import com.example.mymusic_backend.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
