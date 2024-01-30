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
public class WeatherValuesEntity {

    @Id
    private String id;
    private String location;
    private String time;
    private double humidityAvg;
    private double humidityMax;
    private double humidityMin;
    private String moonriseTime;
    private String moonsetTime;
    private double rainIntensityAvg;
    private double snowIntensityAvg;
    private String sunriseTime;
    private String sunsetTime;
    private double temperatureAvg;
    private double temperatureMax;
    private double temperatureMin;
    private double windSpeedAvg;
}
