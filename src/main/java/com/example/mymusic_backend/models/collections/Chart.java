package com.example.mymusic_backend.models.collections;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Chart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chart_id")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "chart_ranking", joinColumns = @JoinColumn(name = "chart_id"))
    @MapKeyColumn(name = "number")
    @Column(name = "ranking")
    private Map<Integer, Long> ranking = new HashMap<>();

    @Enumerated(value = EnumType.STRING)
    private Period period;

    private LocalDate dateChartStart;
    private LocalDate dateChartEnd;


}
