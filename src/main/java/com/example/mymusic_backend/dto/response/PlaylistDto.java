package com.example.mymusic_backend.dto.response;



import com.example.mymusic_backend.models.collections.Playlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlaylistDto {
    private Long id;

    private String name;

    private Long creatorId;
    private String creatorName;

    private String logoLink;

    private Long popularity;


    public static PlaylistDto getDto(Playlist playlist){
        return PlaylistDto.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .creatorName(playlist.getUser().getName())
                .creatorId(playlist.getUser().getId())
                .logoLink(playlist.getLogoLink().getLink())
                .popularity(playlist.getPopularity())
                .build();
    }
}
