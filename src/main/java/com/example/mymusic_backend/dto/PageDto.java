package com.example.mymusic_backend.dto;

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
public class PageDto {
    private int size;
    private int page;
    private int numberOfElements;
    private long totalElements;
    private List<?> list;

    public static PageDto getPageDto(Page<?> page, List<?> listDtos){
        return PageDto.builder()
                .page(page.getNumber())
                .size(page.getSize())
                .list(listDtos)
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .build();
    }

}
