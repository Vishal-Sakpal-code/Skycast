package com.spring.project.skycast.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyCityWeatherEntity {

    @Id
    private String city;
    private int humidity;
    private int precipitationProbability;
    private int rainIntensity;
    private int snowIntensity;
    private double temperature;
    private int uvHealthConcern;
    private int uvIndex;
    private double visibility;
    private double windDirection;
    private double windSpeed;
}
