package com.example.mymusic_backend.dto.request;


import com.example.mymusic_backend.models.Image;
import com.example.mymusic_backend.models.Music;
import com.example.mymusic_backend.models.collections.Playlist;
import com.example.mymusic_backend.models.collections.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaylistRequestDto {

    private String name;

    private Long userId;

    private String logoLink;

    public Playlist getPlaylist(User user, Image image){
        return Playlist.builder()
                .name(this.name)
                .logoLink(image)
                .user(user)
                .build();
    }
}
