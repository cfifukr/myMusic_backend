package com.example.mymusic_backend.dto.request;

import com.example.mymusic_backend.models.Artist;
import com.example.mymusic_backend.models.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtistRequestDto {

    private String name;
    private String image;

    public Artist getArtist(Image image){
        return Artist.builder()
                .logo(image)
                .name(this.name)
                .build();
    }
}
