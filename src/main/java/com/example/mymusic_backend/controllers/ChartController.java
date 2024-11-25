package com.example.mymusic_backend.controllers;


import com.example.mymusic_backend.dto.request.ChartRequestDto;

import com.example.mymusic_backend.dto.response.ChartDto;
import com.example.mymusic_backend.dto.response.MusicDto;
import com.example.mymusic_backend.exceptions.MusicNotFoundExeption;
import com.example.mymusic_backend.models.Music;
import com.example.mymusic_backend.models.collections.Chart;
import com.example.mymusic_backend.models.collections.Period;
import com.example.mymusic_backend.services.ChartService;
import com.example.mymusic_backend.services.MusicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/chart")
@CrossOrigin
public class ChartController {
    private final ChartService chartService;
    private final MusicService musicService;


    public ChartController(ChartService chartService,
                           MusicService musicService){
        this.chartService = chartService;
        this.musicService = musicService;
    }

    @GetMapping("/{chartId}")
    public ResponseEntity<?> getChartById(@PathVariable("chartId") Long id){
        Optional<Chart> chartOpt = chartService.getChartById(id);
        if(chartOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Chart chart = chartOpt.get();
        Map<Integer, Long> ranking = chart.getRanking();
        Map<Integer, Music> rankingMusic = new HashMap<>();

        for(Integer key : chart.getRanking().keySet()){

            Music music = musicService.getMusicById(ranking.get(key));
            if(music == null){
                throw new MusicNotFoundExeption("Music from ranking doesnt exist");
            }
            rankingMusic.put(key, music);
        }
        return ResponseEntity.ok(ChartDto.getDto(chart, rankingMusic));
    }

    @GetMapping
    public ResponseEntity<?> getChartByPeriodAndDate(@RequestParam("date") LocalDate localDate,
                                                     @RequestParam("period") String periodString){
        Period period = Period.valueOf(periodString.toUpperCase().trim());
        if(period == null){
            throw new RuntimeException("Illegal date argument or period");
        }

        LocalDate date = localDate;
        if(localDate == null){
            date= LocalDate.now();
        }

        Optional<Chart> chartOpt = chartService.getChartByDayAndPeriodType(date, period);
        if(chartOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        Chart chart = chartOpt.get();
        Map<Integer, Long> ranking = chart.getRanking();
        Map<Integer, Music> rankingMusic = new HashMap<>();


        System.out.println(chart);

        for(Integer key : chart.getRanking().keySet()){

            Music music = musicService.getMusicById(ranking.get(key));
            if(music == null){
                throw new MusicNotFoundExeption("Music from ranking doesnt exist");
            }
            rankingMusic.put(key, music);
        }
        return ResponseEntity.ok(ChartDto.getDto(chart, rankingMusic));
    }


    @PostMapping
    public ResponseEntity<?> createChart(@RequestBody ChartRequestDto dto){
        return ResponseEntity.ok(chartService.createChart(dto));

    }


    // Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'DELETE' is not supported]
    @RequestMapping(value="/{chartId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteChart(@PathVariable("chartId") Long id){
        chartService.deleteChartById(id);
        return ResponseEntity.ok("Chart deleted succesfully");

    }
}
