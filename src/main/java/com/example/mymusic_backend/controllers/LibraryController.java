package com.example.mymusic_backend.controllers;


import com.example.mymusic_backend.dto.response.LibraryDto;
import com.example.mymusic_backend.services.LibraryService;
import com.example.mymusic_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/library/{userId}")
@CrossOrigin
public class LibraryController {
    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService){
        this.libraryService = libraryService;
    }

    @GetMapping
    public ResponseEntity<LibraryDto> getLibrary(@PathVariable("userId") Long userId){
        LibraryDto data = libraryService.getLibraryDto(userId);

        return  ResponseEntity.ok(data);
    }

    @PostMapping("/add_artist/{artistId}")
    public ResponseEntity<?> addArtistToLibrary(@PathVariable("userId") Long userId,
                                                         @PathVariable("artistId") Long artistId){
        boolean isAdded = libraryService.addArtistToLibrary(userId, artistId);
        return  (isAdded ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }
    @PostMapping("/add_music/{musicId}")
    public ResponseEntity<LibraryDto> addMusicToLibrary(@PathVariable("userId") Long userId,
                                                        @PathVariable("musicId") Long musicId){

        boolean isAdded = libraryService.addMusicToLibrary(userId, musicId);
        return  (isAdded ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }
    @PostMapping("/add_playlist/{playlistId}")
    public ResponseEntity<LibraryDto> addPlaylistLibrary(@PathVariable("userId") Long userId,
                                                         @PathVariable("playlistId") Long playlistId){


        boolean isAdded = libraryService.addPlaylistToLibrary(userId, playlistId);
        return  (isAdded ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }
}
