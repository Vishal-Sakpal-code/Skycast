package com.spring.project.skycast.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Values {

    private double cloudBase;
    private double cloudCeiling;
    private int cloudCover;
    private double dewPoint;
    private int freezingRainIntensity;
    private int humidity;
    private int precipitationProbability;
    private double pressureSurfaceLevel;
    private int rainIntensity;
    private int sleetIntensity;
    private int snowIntensity;
    private double temperature;
    private double temperatureApparent;
    private int uvHealthConcern;
    private int uvIndex;
    private double visibility;
    private int weatherCode;
    private double windDirection;
    private double windGust;
    private double windSpeed;
}
