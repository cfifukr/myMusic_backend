package com.example.mymusic_backend.controllers;


import com.example.mymusic_backend.dto.request.ArtistRequestDto;
import com.example.mymusic_backend.dto.response.ArtistDto;
import com.example.mymusic_backend.models.Artist;
import com.example.mymusic_backend.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artist")
@CrossOrigin
public class ArtistConroller {

    private final ArtistService artistService;

    @Autowired
    public ArtistConroller(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<?> getArtist(@PathVariable("artistId") Long artistId){

        Artist artist = artistService.getArtistById(artistId);
        return ResponseEntity.ok(ArtistDto.getDto(artist));

    }

    @PostMapping
    public ResponseEntity<?> createArtist(@RequestBody ArtistRequestDto dto){

        Artist artist = artistService.createArtist(dto);

        return ResponseEntity.ok(ArtistDto.getDto(artist));
    }
}
