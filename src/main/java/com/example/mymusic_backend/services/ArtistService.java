package com.example.mymusic_backend.services;


import com.example.mymusic_backend.dto.request.ArtistRequestDto;
import com.example.mymusic_backend.dto.response.ArtistDto;
import com.example.mymusic_backend.models.Artist;
import com.example.mymusic_backend.models.Image;
import com.example.mymusic_backend.repositories.ArtistRepository;
import com.example.mymusic_backend.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ImageRepository imageRepository;


    @Autowired
    public ArtistService(ArtistRepository artistRepository,
                         ImageRepository imageRepository){
        this.artistRepository = artistRepository;
        this.imageRepository = imageRepository;
    }

    public Artist getArtistById(Long id){
        return artistRepository.findById(id).orElse(null);
    }

    public Artist saveArtist(Artist artist){
        return artistRepository.save(artist);
    }

    public Artist createArtist(ArtistRequestDto artistRequestDto){
        Image image = imageRepository.save(new Image(artistRequestDto.getImage()));
        Artist artist = artistRequestDto.getArtist(image);

        return  artistRepository.save(artist);
    }
}
