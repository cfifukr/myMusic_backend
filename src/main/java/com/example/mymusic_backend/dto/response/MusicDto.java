package com.example.mymusic_backend.dto.response;

import com.example.mymusic_backend.models.Album;
import com.example.mymusic_backend.models.Artist;
import com.example.mymusic_backend.models.Image;
import com.example.mymusic_backend.models.Music;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MusicDto {


    private Long id;

    private String name;

    private Long  artistId;
    private String  artist;

    private Long albumId;
    private String album;


    private Long nextMusicId;
    private Long prevMusicId;

    private Long listens;


    private String logoLink;

    private Integer secondsDuration;

    public static MusicDto getDto(Music music){

        var result = MusicDto.builder()
                    .id(music.getId())
                    .name(music.getName())
                    .artist(music.getArtist().getName())
                    .artistId(music.getArtist().getId())
                    .album(music.getAlbum().getName())
                    .albumId(music.getAlbum().getId())
                    .prevMusicId(music.getId()-1)
                    .nextMusicId(music.getId()+1)
                    .secondsDuration(music.getSecondsDuration())
                    .listens(music.getTimesListened())
                .build();
        if(music.getLogo() != null){
            result.setLogoLink(music.getLogo().getLink());
        }

        return result;

    }


}
