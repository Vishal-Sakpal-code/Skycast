package com.spring.project.skycast.repository;

import com.spring.project.skycast.entity.WeatherValuesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyForecastRepository extends JpaRepository<WeatherValuesEntity,String> {

    WeatherValuesEntity findByLocationAndTime(String location,String time);
}
