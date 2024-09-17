package com.example.mymusic_backend.storage;

import com.example.mymusic_backend.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
public class LocalStorageService implements StorageService {
    private final MusicRepository musicRepository;

    @Autowired
    public LocalStorageService(MusicRepository musicRepository){
        this.musicRepository = musicRepository;
    }

    private static final Path DIRECTORY = Paths.get("/Users/prosto/Desktop/pet-projects/myMusic/MyMusic_backend/src/main/resources/storage/music");

    @Override
    public byte[] getMusicStream(Long musicId, HttpHeaders headers) {
        System.out.println(musicId + " - getting stream (LocalStorage)");
        musicRepository.addOneListenToMusic(musicId);


        Path file = DIRECTORY.resolve(musicId + ".mp3");

        // Check if file exists and is readable
        if (!Files.exists(file) || !Files.isReadable(file)) {
            return null; // or throw a custom exception
        }

        try {
            long fileSize = Files.size(file);
            Optional<HttpRange> range = headers.getRange().stream().findFirst();

            long start = 0;
            long end = fileSize - 1;

            if (range.isPresent()) {
                HttpRange httpRange = range.get();
                // Get the start and end from the range
                start = httpRange.getRangeStart(fileSize);
                end = httpRange.getRangeEnd(fileSize);

                if (start >= fileSize) {
                    return null; // or throw a custom exception
                }

                end = Math.min(end, fileSize - 1);
            }

            byte[] bytes = Files.readAllBytes(file);
            byte[] rangeBytes = new byte[(int) (end - start + 1)];
            System.arraycopy(bytes, (int) start, rangeBytes, 0, rangeBytes.length);

            return rangeBytes;

        } catch (IOException e) {
            e.printStackTrace();
            return null; // or throw a custom exception
        }
    }

    @Override
    public void putMusic() {
        // Implementation needed for storing music files
    }
}
