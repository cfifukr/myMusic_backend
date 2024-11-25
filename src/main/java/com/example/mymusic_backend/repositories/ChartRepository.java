package com.example.mymusic_backend.repositories;

import com.example.mymusic_backend.models.collections.Chart;
import com.example.mymusic_backend.models.collections.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface ChartRepository extends JpaRepository<Chart, Long> {

    @Query("SELECT x FROM Chart x WHERE :date BETWEEN x.dateChartStart AND x.dateChartEnd AND x.period = :period")
    Optional<Chart> getChartByDayAndPeriod(@Param("date") LocalDate date,
                                           @Param("period") Period period);


}
