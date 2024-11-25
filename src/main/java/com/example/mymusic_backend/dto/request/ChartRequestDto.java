package com.example.mymusic_backend.dto.request;


import com.example.mymusic_backend.models.collections.Chart;
import com.example.mymusic_backend.models.collections.Period;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartRequestDto {

    private Map<Integer, Long> ranking = new HashMap<>();
    private String period;
    private LocalDate dateChartStart;
    private LocalDate dateChartEnd;


}
