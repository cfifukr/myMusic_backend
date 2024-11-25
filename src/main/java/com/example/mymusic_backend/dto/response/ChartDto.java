package com.example.mymusic_backend.dto.response;


import com.example.mymusic_backend.models.Music;
import com.example.mymusic_backend.models.collections.Chart;
import com.example.mymusic_backend.models.collections.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChartDto {
    private Long id;
    private Map<Integer, MusicDto> ranking = new HashMap<>();
    private String  period;
    private LocalDate dateChartStart;
    private LocalDate dateChartEnd;

    public static ChartDto getDto(Chart chart, Map<Integer, Music> ranking){
        Map<Integer, MusicDto> rankingDto = new HashMap<>();

        for(Integer key : ranking.keySet()){
            rankingDto.put(key,
                    MusicDto.getDto(ranking.get(key)));
        }

        return ChartDto.builder()
                .id(chart.getId())
                .period(chart.getPeriod().name())
                .ranking(rankingDto)
                .dateChartStart(chart.getDateChartStart())
                .dateChartEnd(chart.getDateChartEnd())
                .build();

    }
}
