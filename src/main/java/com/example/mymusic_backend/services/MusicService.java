package com.example.mymusic_backend.services;

import com.example.mymusic_backend.dto.request.MusicRequestDto;
import com.example.mymusic_backend.models.Album;
import com.example.mymusic_backend.models.Artist;
import com.example.mymusic_backend.models.Image;
import com.example.mymusic_backend.models.Music;
import com.example.mymusic_backend.repositories.AlbumRepository;
import com.example.mymusic_backend.repositories.ArtistRepository;
import com.example.mymusic_backend.repositories.ImageRepository;
import com.example.mymusic_backend.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MusicService {
    private final MusicRepository musicRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final ImageRepository imageRepository;


    @Autowired
    public MusicService(MusicRepository musicRepository,
                        AlbumRepository albumRepository,
                        ArtistRepository artistRepository,
                        ImageRepository imageRepository){
        this.musicRepository = musicRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.imageRepository = imageRepository;
    }

    @Transactional
    public Music saveMusic(Music music){
        return musicRepository.save(music);
    }

    @Transactional
    public Music addMusic(MusicRequestDto musicDto){
        Album album =  albumRepository.findById(musicDto.getAlbumId())
                .orElseThrow(()-> new IllegalArgumentException("No album with this id (music service)"));
        Artist artist = artistRepository.findById(musicDto.getArtistId())
                .orElseThrow(()-> new IllegalArgumentException("No album with this id (music service)"));
        Image image = imageRepository.save(new Image(musicDto.getLogoLink()));
        Music music = musicDto.getMusic(album, artist, image);

        return musicRepository.save(music);
    }

    @Transactional(readOnly = true)
    public Music getMusicById(Long id){
        return musicRepository.findById(id).orElseThrow(()-> new RuntimeException("No music with this id"));
    }

    @Transactional(readOnly = true)
    public Page<Music> getMusicByArtistId(Long artistId, int size, int number){
        Pageable pageable = PageRequest.of(number, size);

        return musicRepository.getMusicByArtistId(artistId, pageable);

    }
    @Transactional(readOnly = true)
    public Page<Music> getMusicByAlbumId(Long albumId, int size, int number){
        Pageable pageable = PageRequest.of(number, size);

        return musicRepository.getMusicByAlbumId(albumId, pageable);

    }

}
