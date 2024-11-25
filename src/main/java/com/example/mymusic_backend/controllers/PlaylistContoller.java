package com.example.mymusic_backend.controllers;


import com.example.mymusic_backend.dto.request.PlaylistRequestDto;
import com.example.mymusic_backend.dto.response.MusicDto;
import com.example.mymusic_backend.dto.response.PlaylistDto;
import com.example.mymusic_backend.models.Music;
import com.example.mymusic_backend.models.collections.Playlist;
import com.example.mymusic_backend.services.MusicService;
import com.example.mymusic_backend.services.PlaylistService;
import com.example.mymusic_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/playlist")
public class PlaylistContoller {
    private final PlaylistService playlistService;
    private final MusicService musicService;


    @Autowired
    public PlaylistContoller(PlaylistService playlistService,
                             MusicService musicService){
        this.playlistService = playlistService;
        this.musicService = musicService;
    }


    @PostMapping
    public ResponseEntity<PlaylistDto> createPlaylist(@RequestBody PlaylistRequestDto playlistRequestDto){
        Playlist playlist = playlistService.createPlaylist(playlistRequestDto);

        return ResponseEntity.ok(PlaylistDto.getDto(playlist));
    }

    @GetMapping
    public ResponseEntity<PlaylistDto> getPlaylistData(@RequestParam("id") Long playlistId){
        Playlist playlist = playlistService.getPlaylistById(playlistId);

        return ResponseEntity.ok(PlaylistDto.getDto(playlist));
    }

    @GetMapping("/{playlistId}")
    public ResponseEntity<List<MusicDto>> getPlaylistMusicData(@PathVariable("playlistId") Long playlistId,
                                                            @RequestParam(name = "size", defaultValue = "20") int size,
                                                            @RequestParam(name = "page", defaultValue = "0") int page){
        Playlist playlist = playlistService.getPlaylistById(playlistId);

        Set<Music> musics = playlist.getMusicSet();

        List<MusicDto> musicsDtos = musics.stream()
                .map(MusicDto::getDto)
                .toList();

        return ResponseEntity.ok(musicsDtos);
    }


    @PostMapping("/{playlistId}")
    public ResponseEntity<?> addPlaylistToUser(@PathVariable("playlistId") Long playlistId,
                                                @RequestParam(value = "userId", required = true) Long userId){

        boolean isAdded = playlistService.addPlaylistToUser(playlistId, userId);

        return (isAdded ? ResponseEntity.ok(null) : ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
    @PutMapping("/{playlistId}")
    public ResponseEntity<?> addMusicToPlaylist(@PathVariable("playlistId") Long playlistId,
                                                       @RequestParam("musicId") Long musicId){
        boolean isAdded = playlistService.addMusicToPlaylist(musicId, playlistId);
        if(isAdded){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPlayersPlaylist(@PathVariable("userId") Long userId){
        Set<Playlist> playlists = playlistService.getUserPlaylists(userId);

        return ResponseEntity.ok(
                playlists.stream()
                        .map(PlaylistDto::getDto)
                        .toList());
    }
}