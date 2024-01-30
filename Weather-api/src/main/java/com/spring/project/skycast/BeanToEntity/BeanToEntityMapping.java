package com.spring.project.skycast.BeanToEntity;

import com.spring.project.skycast.bean.WeatherValues;
import com.spring.project.skycast.entity.WeatherValuesEntity;
import org.springframework.stereotype.Component;

@Component
public class BeanToEntityMapping {

    public WeatherValuesEntity BeanToEntityWeatherValues(WeatherValues weatherValues) {
        WeatherValuesEntity weatherValuesEntity = new WeatherValuesEntity();
        weatherValuesEntity.setHumidityAvg(weatherValues.getHumidityAvg());
        weatherValuesEntity.setHumidityMax(weatherValues.getHumidityMax());
        weatherValuesEntity.setHumidityMin(weatherValues.getHumidityMin());
        weatherValuesEntity.setMoonsetTime(weatherValues.getMoonsetTime());
        weatherValuesEntity.setMoonriseTime(weatherValues.getMoonriseTime());
        weatherValuesEntity.setSunsetTime(weatherValues.getSunsetTime());
        weatherValuesEntity.setSunriseTime(weatherValues.getSunriseTime());
        weatherValuesEntity.setRainIntensityAvg(weatherValues.getRainIntensityAvg());
        weatherValuesEntity.setSnowIntensityAvg(weatherValues.getSnowIntensityAvg());
        weatherValuesEntity.setTemperatureAvg(weatherValues.getTemperatureAvg());
        weatherValuesEntity.setHumidityMin(weatherValues.getHumidityMin());
        weatherValuesEntity.setTemperatureMax(weatherValues.getTemperatureMax());
        weatherValuesEntity.setWindSpeedAvg(weatherValues.getWindSpeedAvg());
        return weatherValuesEntity;
    }
}
