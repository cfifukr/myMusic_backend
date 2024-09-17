package com.example.mymusic_backend.services;


import com.example.mymusic_backend.models.Genre;
import com.example.mymusic_backend.models.Music;
import com.example.mymusic_backend.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenresService {
    private final MusicRepository musicRepository;

    @Autowired
    public GenresService(MusicRepository musicRepository){
        this.musicRepository = musicRepository;
    }

    public Page<Music> getMusicByGenres(List<String> genresString,
                                        int pageNumber,
                                        int pageSize){
        List<Genre> genresList = new ArrayList<>();
        for(String genreStr : genresString){
            try {
                Genre genre = Genre.valueOf(genreStr.trim().toUpperCase()
                        .replaceAll("-", ""));
                genresList.add(genre);
            }catch (IllegalArgumentException err){
                System.out.println(err);
            }
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return musicRepository.findMostPopularMusicByGenres(genresList, pageable);

    }
}
