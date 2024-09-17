package com.example.mymusic_backend.services;

import com.example.mymusic_backend.models.Album;
import com.example.mymusic_backend.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    public Album getAlbumById(Long id){
        return albumRepository.findById(id).orElse(null);
    }
    public Album saveAlbum(Album album){
        return albumRepository.save(album);
    }
}
