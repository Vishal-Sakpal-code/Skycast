package com.spring.project.skycast.BeanToEntity;

import com.spring.project.skycast.bean.DailyCityWeather;
import com.spring.project.skycast.entity.DailyCityWeatherEntity;
import org.springframework.stereotype.Component;

@Component
public class DailyWeatherDataConvertor {



    public DailyCityWeatherEntity dtoToEntity(DailyCityWeather dailyCityWeather)
    {
        DailyCityWeatherEntity dailyCityWeatherEntity= new DailyCityWeatherEntity();
        dailyCityWeatherEntity.setHumidity(dailyCityWeather.getData().getValues().getHumidity());
        dailyCityWeatherEntity.setTemperature(dailyCityWeather.getData().getValues().getTemperature());
        dailyCityWeatherEntity.setSnowIntensity(dailyCityWeather.getData().getValues().getSnowIntensity());
        dailyCityWeatherEntity.setRainIntensity(dailyCityWeather.getData().getValues().getRainIntensity());
        dailyCityWeatherEntity.setUvIndex(dailyCityWeather.getData().getValues().getUvIndex());
        dailyCityWeatherEntity.setVisibility(dailyCityWeather.getData().getValues().getVisibility());
        dailyCityWeatherEntity.setPrecipitationProbability(dailyCityWeather.getData().getValues().getPrecipitationProbability());
        dailyCityWeatherEntity.setUvHealthConcern(dailyCityWeather.getData().getValues().getUvHealthConcern());
        dailyCityWeatherEntity.setWindDirection(dailyCityWeather.getData().getValues().getWindDirection());
        dailyCityWeatherEntity.setWindSpeed(dailyCityWeather.getData().getValues().getWindSpeed());

        return dailyCityWeatherEntity;
    }

}
