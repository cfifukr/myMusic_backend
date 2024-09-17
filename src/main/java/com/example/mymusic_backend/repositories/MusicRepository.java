package com.example.mymusic_backend.repositories;

import com.example.mymusic_backend.models.Genre;
import com.example.mymusic_backend.models.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {

    @Query("SELECT x FROM Music x WHERE x.artist.id =:artist")
    Page<Music> getMusicByArtistId(@Param("artist") Long id, Pageable pageable);

    @Query("SELECT x FROM Music x WHERE x.album.id =:album")
    Page<Music> getMusicByAlbumId(@Param("album") Long id, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Music m  SET m.timesListened = m.timesListened + 1 WHERE m.id = :musicId")
    void addOneListenToMusic(@Param("musicId") Long id);
    
    
    @Query("SELECT m FROM Music m ORDER BY m.timesListened DESC")
    Page<Music> findMostPopularMusic(Pageable pageable);


    @Query("SELECT m FROM Music m WHERE m.genre IN :genreList")
    Page<Music> findRandomMusicByGenres(@Param("genreList") List<Genre> genresList,
                                 Pageable pageable);

    @Query("SELECT m FROM Music m WHERE m.genre IN :genreList ORDER BY m.timesListened DESC")
    Page<Music> findMostPopularMusicByGenres(@Param("genreList") List<Genre> genresList,
                                        Pageable pageable);
}
