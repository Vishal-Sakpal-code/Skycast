package com.spring.project.skycast.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dailylocationdata {

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
