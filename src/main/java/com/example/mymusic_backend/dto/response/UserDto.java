package com.example.mymusic_backend.dto.response;

import com.example.mymusic_backend.models.collections.User;

import lombok.Builder;
import lombok.Data;




@Builder
@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private Long popularity;

    public static UserDto getDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .popularity(user.getPopularity())
                .build();
    }


}
