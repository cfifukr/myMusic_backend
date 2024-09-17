package com.example.mymusic_backend.dto.request;

import com.example.mymusic_backend.models.collections.Playlist;
import com.example.mymusic_backend.models.collections.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDto {


    private String email;

    private String name;

    private String password;

    public User getUser(){
        return User.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .build();
    }


}
