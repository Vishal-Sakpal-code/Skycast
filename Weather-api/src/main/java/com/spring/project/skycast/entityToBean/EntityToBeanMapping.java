package com.spring.project.skycast.entityToBean;

import com.spring.project.skycast.bean.*;
import com.spring.project.skycast.entity.*;
import org.springframework.stereotype.Component;

@Component
public class EntityToBeanMapping {


    public  Dailylocationdata mapEntityToBeanForDailyDataByLocation(DailyCityWeatherEntity dailyCityWeatherEntity)
    {
        Dailylocationdata dailylocationdata = new Dailylocationdata();
        dailylocationdata.setHumidity(dailyCityWeatherEntity.getHumidity());
        dailylocationdata.setTemperature(dailyCityWeatherEntity.getTemperature());
        dailylocationdata.setVisibility(dailyCityWeatherEntity.getVisibility());
        dailylocationdata.setSnowIntensity(dailyCityWeatherEntity.getSnowIntensity());
        dailylocationdata.setUvIndex(dailyCityWeatherEntity.getUvIndex());
        dailylocationdata.setRainIntensity(dailyCityWeatherEntity.getRainIntensity());
        dailylocationdata.setPrecipitationProbability(dailyCityWeatherEntity.getPrecipitationProbability());
        dailylocationdata.setUvHealthConcern(dailyCityWeatherEntity.getUvHealthConcern());
        dailylocationdata.setWindDirection(dailyCityWeatherEntity.getWindDirection());
        dailylocationdata.setWindSpeed(dailyCityWeatherEntity.getWindSpeed());
        return dailylocationdata;
    }

    public Locationforecastdata mapEntityToBeanForLocationForecast(WeatherValuesEntity weatherValues)
    {
        Locationforecastdata locationforecastdata = new Locationforecastdata();
        locationforecastdata.setHumidityAvg(weatherValues.getHumidityAvg());;
        locationforecastdata.setHumidityMax(weatherValues.getHumidityMax());
        locationforecastdata.setHumidityMin(weatherValues.getHumidityMin());
        locationforecastdata.setMoonriseTime(weatherValues.getMoonriseTime());
        locationforecastdata.setMoonsetTime(weatherValues.getMoonsetTime());
        locationforecastdata.setSunriseTime(weatherValues.getSunriseTime());
        locationforecastdata.setSunsetTime(weatherValues.getSunsetTime());
        locationforecastdata.setTemperatureAvg(weatherValues.getTemperatureAvg());
        locationforecastdata.setTemperatureMax(weatherValues.getTemperatureMax());
        locationforecastdata.setTemperatureMin(weatherValues.getTemperatureMin());
        locationforecastdata.setRainIntensityAvg(weatherValues.getRainIntensityAvg());
        locationforecastdata.setSnowIntensityAvg(weatherValues.getSnowIntensityAvg());
        locationforecastdata.setWindSpeedAvg(weatherValues.getWindSpeedAvg());
        return locationforecastdata;
    }
}
