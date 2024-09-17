package com.example.mymusic_backend.repositories;

import com.example.mymusic_backend.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
