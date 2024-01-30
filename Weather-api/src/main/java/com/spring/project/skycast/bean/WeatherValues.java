package com.spring.project.skycast.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherValues {
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
