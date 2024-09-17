package com.example.mymusic_backend.dto.request;


import com.example.mymusic_backend.models.Album;
import com.example.mymusic_backend.models.Artist;
import com.example.mymusic_backend.models.Image;
import com.example.mymusic_backend.models.Music;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MusicRequestDto {


    private String name;

    private Long artistId;

    private Long albumId;

    private String logoLink;

    private Integer secondsDuration;

    public Music getMusic(Album album, Artist artist, Image image){
        Image logoImage = new Image();
        logoImage.setLink(logoLink);

        Music music = new Music();
        music.setName(this.name);
        music.setAlbum(album);
        music.setArtist(artist);
        music.setLogo(image);

        return  music;
    }


}
