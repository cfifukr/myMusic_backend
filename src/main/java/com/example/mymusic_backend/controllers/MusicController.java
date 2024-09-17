package com.example.mymusic_backend.controllers;

import com.example.mymusic_backend.dto.request.MusicRequestDto;
import com.example.mymusic_backend.dto.response.MusicDto;

import com.example.mymusic_backend.models.Music;

import com.example.mymusic_backend.services.MusicService;
import com.example.mymusic_backend.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/music")
@CrossOrigin
public class MusicController {

    private final StorageService storageService;
    private final MusicService musicService;



    @Autowired
    public MusicController(MusicService musicService,
                           StorageService storageService){
        this.musicService = musicService;
        this.storageService = storageService;

    }

    @GetMapping("/{musicId}")
    public ResponseEntity<?> getMusic(@PathVariable Long musicId){

        Music music = musicService.getMusicById(musicId);
        return ResponseEntity.ok(MusicDto.getDto(music));


    }

    @GetMapping("/stream/{musicId}")
    public ResponseEntity<byte[]> streamMusic(@PathVariable Long musicId,
                                              @RequestHeader HttpHeaders headers) {
        List<HttpRange> ranges = headers.getRange();
        HttpHeaders responseHeaders = new HttpHeaders();
        byte[] musicBytes = storageService.getMusicStream(musicId, headers);

        if (musicBytes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (!ranges.isEmpty()) {
            HttpRange range = ranges.get(0);
            long start = range.getRangeStart(musicBytes.length);
            long end = range.getRangeEnd(musicBytes.length);

            if (start >= musicBytes.length) {
                return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
                        .header(HttpHeaders.CONTENT_RANGE, "bytes */" + musicBytes.length)
                        .build();
            }

            end = Math.min(end, musicBytes.length - 1);

            byte[] rangeBytes = new byte[(int) (end - start + 1)];
            System.arraycopy(musicBytes, (int) start, rangeBytes, 0, rangeBytes.length);

            responseHeaders.add(HttpHeaders.CONTENT_RANGE, "bytes " + start + "-" + end + "/" + musicBytes.length);
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .headers(responseHeaders)
                    .body(rangeBytes);
        }

        responseHeaders.add(HttpHeaders.CONTENT_TYPE, "audio/mpeg");
        responseHeaders.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(musicBytes.length));
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(musicBytes);
    }


    @PostMapping
    public ResponseEntity<?> addMusic(@RequestBody MusicRequestDto dto ){

        Music music =  musicService.addMusic(dto);
        return ResponseEntity.ok(MusicDto.getDto(music));
    }
}
