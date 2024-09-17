package com.example.mymusic_backend;

import com.example.mymusic_backend.models.Album;
import com.example.mymusic_backend.models.Artist;
import com.example.mymusic_backend.services.AlbumService;
import com.example.mymusic_backend.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;

@SpringBootApplication
public class MyMusicBackendApplication {




    public static void main(String[] args) {
        SpringApplication.run(MyMusicBackendApplication.class, args);
    }


}
