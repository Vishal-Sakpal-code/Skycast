package com.spring.project.skycast.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Locationforecastdata {
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
