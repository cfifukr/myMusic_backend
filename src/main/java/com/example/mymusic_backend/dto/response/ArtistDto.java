package com.example.mymusic_backend.dto.response;

import com.example.mymusic_backend.models.Album;
import com.example.mymusic_backend.models.Artist;
import com.example.mymusic_backend.models.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ArtistDto {
    private Long id;

    private String name;

    private String logoLink;

    public static ArtistDto getDto(Artist artist){
        return ArtistDto.builder()
                .id(artist.getId())
                .name(artist.getName())
                .logoLink(artist.getLogo().getLink())
                .build();
    }


}
