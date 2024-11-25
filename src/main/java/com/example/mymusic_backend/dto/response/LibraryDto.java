package com.example.mymusic_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LibraryDto {

    public List<ArtistDto> artistList;
    public List<PlaylistDto> playlistList;
    public List<MusicDto> musicList;




}
