package com.spring.project.skycast.service;

import com.spring.project.skycast.BeanToEntity.DailyWeatherDataConvertor;
import com.spring.project.skycast.Feign.DailyWeather;
import com.spring.project.skycast.bean.DailyCityWeather;
import com.spring.project.skycast.bean.Dailylocationdata;
import com.spring.project.skycast.constants.APIConstants;
import com.spring.project.skycast.entity.DailyCityWeatherEntity;
import com.spring.project.skycast.entityToBean.EntityToBeanMapping;
import com.spring.project.skycast.repository.DailyWeatherRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class DailyWeatherService {

    private static final Logger logger = LoggerFactory.getLogger(DailyWeatherService.class);
    private DailyWeather dailyWeather;
    private DailyWeatherDataConvertor dailyWeatherDataConvertor;
    private DailyWeatherRepository dailyWeatherRepository;
    private Dailylocationdata dailylocationdata;

    private EntityToBeanMapping entityToBeanMapping;


    @Autowired
    public DailyWeatherService(DailyWeather dailyWeather, DailyWeatherDataConvertor dailyWeatherDataConvertor, DailyWeatherRepository dailyWeatherRepository, Dailylocationdata dailylocationdata, EntityToBeanMapping entityToBeanMapping) {
        this.dailyWeather = dailyWeather;
        this.dailyWeatherDataConvertor = dailyWeatherDataConvertor;
        this.dailyWeatherRepository = dailyWeatherRepository;
        this.dailylocationdata = dailylocationdata;
        this.entityToBeanMapping = entityToBeanMapping;
    }

    private void saveDailyWeatherData(String location)
    {
        try{
            DailyCityWeather dailyCityWeather = dailyWeather.getWeatherData(location,APIConstants.key);
            logger.info("Daily weather data fetched for: " + location);
            if(dailyCityWeather!=null)
            {
                DailyCityWeatherEntity dailyCityWeatherEntity = dailyWeatherDataConvertor.dtoToEntity(dailyCityWeather);
                dailyCityWeatherEntity.setCity(location);
                dailyWeatherRepository.save(dailyCityWeatherEntity);
            }
            else
            {
                logger.info("Data for location: " + location + " does not exist");
            }
        }catch (FeignException e)
        {
            logger.error("failed to fetch data from api: " + e);
            throw new RuntimeException("failed to fetch data from api");
        }catch (HttpClientErrorException e)
        {
            logger.error("Http client error while fetching data: " + e);
            throw new RuntimeException("failed to fetch data from api");
        }catch (DataAccessException e)
        {
            logger.error("Exception while saving data: " + e);
            throw new RuntimeException("failed to fetch data from api");
        }catch (Exception e)
        {
            logger.error("Unexpected error occurred: " + e);
            throw new RuntimeException("Unexpected error occurred");
        }
    }

    public Dailylocationdata fetchDataByLocation(String location)
    {
        saveDailyWeatherData(location);
        DailyCityWeatherEntity dailyCityWeatherEntity = dailyWeatherRepository.getById(location);
        return entityToBeanMapping.mapEntityToBeanForDailyDataByLocation(dailyCityWeatherEntity);
    }
}
