package com.example.mymusic_backend.controllers;

import com.example.mymusic_backend.dto.MusicPageDto;
import com.example.mymusic_backend.dto.PageDto;
import com.example.mymusic_backend.dto.response.MusicDto;
import com.example.mymusic_backend.models.Music;
import com.example.mymusic_backend.services.GenresService;
import com.example.mymusic_backend.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
@CrossOrigin
public class GenresController {

    private final GenresService genresService;

    @Autowired
    public GenresController(GenresService genresService){
        this.genresService = genresService;
    }


    @GetMapping
    public ResponseEntity<?> getMusicByGenres(@RequestParam(value = "genre", required = true) List<String> genres,
                                           @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                           @RequestParam(value = "pageSize", defaultValue = "25") int pageSize){
        Page<Music> musicPage = genresService.getMusicByGenres(genres, pageNumber, pageSize);
        MusicPageDto pageDto = MusicPageDto.getPageDto(musicPage);

        return ResponseEntity.ok(pageDto);
    }
}
