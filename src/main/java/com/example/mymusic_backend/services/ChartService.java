package com.example.mymusic_backend.services;


import com.example.mymusic_backend.dto.request.ChartRequestDto;
import com.example.mymusic_backend.exceptions.MusicNotFoundExeption;
import com.example.mymusic_backend.models.Music;
import com.example.mymusic_backend.models.collections.Chart;
import com.example.mymusic_backend.models.collections.Period;
import com.example.mymusic_backend.repositories.ChartRepository;
import com.example.mymusic_backend.repositories.MusicRepository;
import com.example.mymusic_backend.utils.ChartUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChartService {
    private final ChartRepository chartRepository;
    private final MusicRepository musicRepository;


    public ChartService(ChartRepository chartRepository,
                        MusicRepository musicRepository) {
        this.chartRepository = chartRepository;
        this.musicRepository = musicRepository;
    }


    @Transactional
    public void deleteChartById(Long id){
        chartRepository.deleteById(id);
    }

    @Transactional
    public Chart createChart(ChartRequestDto dto) {

        //all keys from ranking must be sequential and cant be null
        if (!ChartUtils.areKeysSequential(dto.getRanking())) {
            throw new IllegalArgumentException("Ranking keys aren`t sequential");
        }

        //all music from ranking must exist
        if (!existsAllMusicsByIds(dto.getRanking().values().stream().toList())) {
            throw new MusicNotFoundExeption("Music from the ranking doesn`t exist");
        }

        if(getChartByDayAndPeriodType(dto.getDateChartStart(),
                Period.valueOf(dto.getPeriod().toUpperCase().trim())).isPresent()) {
            throw new IllegalArgumentException("Chart already exist");
        }

        Chart chart = Chart.builder()
                .ranking(dto.getRanking())
                .dateChartEnd(dto.getDateChartEnd())
                .dateChartStart(dto.getDateChartStart())
                .period(Period.valueOf(dto.getPeriod().toUpperCase().trim()))
                .build();

        return chartRepository.save(chart);

    }

    @Transactional(readOnly = true)
    public Optional<Chart> getChartById(Long id) {
        return chartRepository.findById(id);
    }


    @Transactional(readOnly = true)
    public boolean existsAllMusicsByIds(List<Long> idList) {

        for (Long id : idList) {
            if (!musicRepository.existsById(id)) {
                return false;
            }
        }
        return true;
    }


    @Transactional(readOnly = true)
    public Optional<Chart> getChartByDayAndPeriodType(LocalDate date, Period period) {
        return chartRepository.getChartByDayAndPeriod(date, period);
    }

}




