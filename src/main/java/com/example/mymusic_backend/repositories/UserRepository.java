package com.example.mymusic_backend.repositories;


import com.example.mymusic_backend.models.Artist;
import com.example.mymusic_backend.models.Music;
import com.example.mymusic_backend.models.collections.Playlist;
import com.example.mymusic_backend.models.collections.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {


    User getUserByEmail(String email);


    @Query("SELECT x.playlists FROM User x WHERE x.id = :userId")
    Set<Playlist> getUserPlaylists(@Param("userId") Long userId);

    @Query("SELECT x.artists FROM User x WHERE x.id = :userId")
    Set<Artist> getUserSavedArtists(@Param("userId") Long userId);

    @Query("SELECT x.musics FROM User x WHERE x.id = :userId")
    Set<Music> getUserSavedMusics(@Param("userId") Long userId);
}
