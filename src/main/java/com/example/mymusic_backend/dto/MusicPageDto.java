package com.example.mymusic_backend.dto;

import com.example.mymusic_backend.dto.response.MusicDto;
import com.example.mymusic_backend.models.Music;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MusicPageDto {
    private int size;
    private int page;
    private int numberOfElements;
    private long totalElements;
    private List<MusicDto> list;

    public static MusicPageDto getPageDto(Page<Music> page){
        return MusicPageDto.builder()
                .page(page.getNumber())
                .size(page.getSize())
                .list(page.stream().map(MusicDto::getDto).toList())
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .build();
    }

}
