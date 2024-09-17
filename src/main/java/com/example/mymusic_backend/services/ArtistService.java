package com.example.mymusic_backend.services;


import com.example.mymusic_backend.models.Artist;
import com.example.mymusic_backend.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public Artist getArtistById(Long id){
        return artistRepository.findById(id).orElse(null);
    }

    public Artist saveArtist(Artist artist){
        return artistRepository.save(artist);
    }
}
