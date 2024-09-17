package com.example.mymusic_backend.repositories;


import com.example.mymusic_backend.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
