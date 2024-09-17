package com.example.mymusic_backend.services;

import com.example.mymusic_backend.dto.request.PlaylistRequestDto;
import com.example.mymusic_backend.models.Image;
import com.example.mymusic_backend.models.Music;
import com.example.mymusic_backend.models.collections.Playlist;
import com.example.mymusic_backend.models.collections.User;
import com.example.mymusic_backend.repositories.ImageRepository;
import com.example.mymusic_backend.repositories.MusicRepository;
import com.example.mymusic_backend.repositories.PlaylistRepository;
import com.example.mymusic_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final MusicRepository musicRepository;

    private final ImageRepository imageRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository,
                           UserRepository userRepository,
                           MusicRepository musicRepository,
                           ImageRepository imageRepository){
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.musicRepository = musicRepository;
        this.imageRepository = imageRepository;
    }

    public Playlist getPlaylistById(Long id){
        return playlistRepository.findById(id).orElse(null);
    }

    public Playlist savePlaylist(Playlist playlist){
        return playlistRepository.save(playlist);
    }

    public Playlist createPlaylist(PlaylistRequestDto playlistRequestDto){
        User user = userRepository.findById(playlistRequestDto.getUserId())
                .orElseThrow(()-> new RuntimeException("No user with such id (playlist controller)"));


        Image image = imageRepository.save(new Image(playlistRequestDto.getLogoLink()));
        Playlist playlist = playlistRepository.save(playlistRequestDto.getPlaylist(user, image));

        //add playlist to set
        user.addPlaylist(playlist);
        userRepository.save(user);

        return playlist;

    }
    public boolean addMusicToPlaylist(Long musicId, Long playlistId){
        Optional<Music> musicOpt = musicRepository.findById(musicId);
        Optional<Playlist> playlistOpt = playlistRepository.findById(playlistId);

        if(musicOpt.isEmpty() || playlistOpt.isEmpty()){
            return false;
        }


        Music music = musicOpt.get();
        Playlist playlist = playlistOpt.get();

        playlist.addMusic(music);
        playlistRepository.save(playlist);

        return  true;


    }
}
