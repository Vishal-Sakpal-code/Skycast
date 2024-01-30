package com.spring.project.skycast.repository;

import com.spring.project.skycast.entity.DailyCityWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyWeatherRepository extends JpaRepository<DailyCityWeatherEntity,String> {
}
