package com.example.mymusic_backend.services;


import com.example.mymusic_backend.dto.response.ArtistDto;
import com.example.mymusic_backend.dto.response.LibraryDto;
import com.example.mymusic_backend.dto.response.MusicDto;
import com.example.mymusic_backend.dto.response.PlaylistDto;
import com.example.mymusic_backend.models.Artist;
import com.example.mymusic_backend.models.Music;
import com.example.mymusic_backend.models.collections.Playlist;
import com.example.mymusic_backend.models.collections.User;
import com.example.mymusic_backend.repositories.ArtistRepository;
import com.example.mymusic_backend.repositories.MusicRepository;
import com.example.mymusic_backend.repositories.PlaylistRepository;
import com.example.mymusic_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.Set;

@Service
public class LibraryService {
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final PlaylistRepository playlistRepository;
    private final MusicRepository musicRepository;

    @Autowired
    public LibraryService(UserRepository userRepository,
                          ArtistRepository artistRepository,
                          PlaylistRepository playlistRepository,
                          MusicRepository musicRepository) {
        this.userRepository = userRepository;
        this.musicRepository = musicRepository;
        this.artistRepository = artistRepository;
        this.playlistRepository = playlistRepository;
    }

    public LibraryDto getLibraryDto(Long userId) {
        Set<Artist> artists = userRepository.getUserSavedArtists(userId);
        Set<Playlist> playlists = userRepository.getUserPlaylists(userId);
        Set<Music> music = userRepository.getUserSavedMusics(userId);

        return LibraryDto.builder()
                .musicList(music
                        .stream()
                        .map(MusicDto::getDto).toList())
                .playlistList(playlists
                        .stream()
                        .map(PlaylistDto::getDto).toList())
                .artistList(artists
                        .stream()
                        .map(ArtistDto::getDto).toList())
                .build();

    }

    public boolean addArtistToLibrary(Long userId, Long artistId){
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) return false;
        User user = userOpt.get();

        Optional<Artist> artistOpt = artistRepository.findById(artistId);
        if(artistOpt.isEmpty()) return false;
        Artist artist = artistOpt.get();

        user.addArtist(artist);

        userRepository.save(user);
        return true;


    }

    public boolean addMusicToLibrary(Long userId, Long musicId){
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) return false;
        User user = userOpt.get();

        Optional<Music> musicOpt = musicRepository.findById(musicId);
        if(musicOpt.isEmpty()) return false;
        Music music = musicOpt.get();

        user.addMusic(music);

        userRepository.save(user);
        return true;


    }


    public boolean addPlaylistToLibrary(Long userId, Long playlistId){
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) return false;
        User user = userOpt.get();

        Optional<Playlist> playlistOpt = playlistRepository.findById(playlistId);
        if(playlistOpt.isEmpty()) return false;
        Playlist playlist = playlistOpt.get();

        user.addPlaylist(playlist);

        userRepository.save(user);
        return true;


    }
}
