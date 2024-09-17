package com.example.mymusic_backend.storage;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

public interface StorageService {

    byte[] getMusicStream(Long musicId, HttpHeaders headers);
    void putMusic();
}
