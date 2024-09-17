package com.example.mymusic_backend.repositories;

import com.example.mymusic_backend.models.Album;
import com.example.mymusic_backend.models.Artist;
import com.example.mymusic_backend.models.Music;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MusicRepositoryTest {

    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @BeforeAll
    void addDefaultAlbumsAndArtist(){
        Album album1 = Album.builder()
                .name("Album 1")
                .musics(new HashSet<>())
                .likes(10000)
                .dislikes(543)
                .build();

        Album album2 = Album.builder()
                .name("Album 2")
                .musics(new HashSet<>())
                .likes(3000)
                .dislikes(43)
                .build();

        Artist artist1 = Artist.builder()
                .name("Artist 1")
                .albums(new HashSet<>(Arrays.asList(album1)))
                .build();

        Artist artist2 = Artist.builder()
                .name("Artist 2")
                .albums(new HashSet<>(Arrays.asList(album2)))
                .build();

        album1 = albumRepository.save(album1);
        album2 = albumRepository.save(album2);
        artist1 = artistRepository.save(artist1);
        artist2 = artistRepository.save(artist2);

        Music music = new Music();
        music.setArtist(artist1);
        music.setAlbum(album2);
        music.setName("Music");
        musicRepository.save(music);

        Music music1 = new Music();
        music1.setArtist(artist1);
        music1.setAlbum(album1);
        music1.setName("Music 1");
        musicRepository.save(music1);

        Music music2 = new Music();
        music2.setArtist(artist1);
        music2.setAlbum(album1);
        music2.setName("Music 2");
        musicRepository.save(music2);

        Music music3 = new Music();
        music3.setArtist(artist2);
        music3.setAlbum(album2);
        music3.setName("Music 3");
        musicRepository.save(music3);

        Music music4 = new Music();
        music4.setArtist(artist2);
        music4.setAlbum(album2);
        music4.setName("Music 4");
        musicRepository.save(music4);

        Music music5 = new Music();
        music5.setArtist(null);
        music5.setAlbum(null);
        music5.setName("Music 5");
        musicRepository.save(music5);


    }

    @Test
    void getAllMusicByArtist(){
        Page<Music> musics1 =  musicRepository.getMusicByArtistId(1L, PageRequest.of(0, 5));

        Page<Music> musics2 =  musicRepository.getMusicByArtistId(2L, PageRequest.of(0, 5));

        assertAll("Two request get all music by artist",
                ()-> assertEquals(3, musics1.getTotalElements()),
                ()-> assertEquals(2, musics2.getTotalElements()));


    }

    @Test
    void getAllMusicByAlbum(){
        Page<Music> musics1 =  musicRepository.getMusicByAlbumId(1L, PageRequest.of(0, 5));

        Page<Music> musics2 =  musicRepository.getMusicByAlbumId(2L, PageRequest.of(0, 5));

        assertAll("Two request get all music by artist",
                ()-> assertEquals(2, musics1.getTotalElements()),
                ()-> assertEquals(3, musics2.getTotalElements()));


    }



}