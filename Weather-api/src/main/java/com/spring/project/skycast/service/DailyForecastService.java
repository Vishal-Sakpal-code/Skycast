package com.spring.project.skycast.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.skycast.BeanToEntity.BeanToEntityMapping;
import com.spring.project.skycast.Feign.DailyForecast;
import com.spring.project.skycast.bean.DailyForecastBean;
import com.spring.project.skycast.bean.DailyTimeline;
import com.spring.project.skycast.bean.TimeLines;
import com.spring.project.skycast.bean.WeatherValues;
import com.spring.project.skycast.entity.WeatherValuesEntity;
import com.spring.project.skycast.entityToBean.EntityToBeanMapping;
import com.spring.project.skycast.repository.DailyForecastRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static com.spring.project.skycast.constants.APIConstants.key;
import static com.spring.project.skycast.constants.APIConstants.timestamp;


@Service
public class DailyForecastService {

    private static final Logger logger = LoggerFactory.getLogger(DailyForecastService.class);
    public DailyForecast dailyForecast;
    public DailyTimeline dailyTimeline;
    public BeanToEntityMapping beanToEntityMapping;
    public EntityToBeanMapping entityToBeanMapping;
    public DailyForecastRepository dailyForecastRepository;


    @Autowired
    public DailyForecastService(DailyForecast dailyForecast,EntityToBeanMapping entityToBeanMapping, DailyTimeline dailyTimeline,BeanToEntityMapping beanToEntityMapping,DailyForecastRepository dailyForecastRepository) {
        this.dailyForecast = dailyForecast;
        this.dailyTimeline = dailyTimeline;
        this.entityToBeanMapping = entityToBeanMapping;
        this.beanToEntityMapping=beanToEntityMapping;
        this.dailyForecastRepository = dailyForecastRepository;
    }


    private void getDailyForecastFromAPI(String location) throws JsonProcessingException
    {
        try {
            ResponseEntity<String> responseEntity = dailyForecast.getdailyForecast(location,key,timestamp);
            if(responseEntity.getStatusCode().is2xxSuccessful())
            {
                ObjectMapper objectMapper = new ObjectMapper();
                DailyForecastBean dailyForecastBean = objectMapper.readValue(responseEntity.getBody(), DailyForecastBean.class);
                TimeLines timeLines = dailyForecastBean.getTimelines();
                for(DailyTimeline values:timeLines.getDaily())
                {
                    String time = values.getTime();
                    WeatherValues weatherValues= values.getValues();
                    WeatherValuesEntity weatherValuesEntity = beanToEntityMapping.BeanToEntityWeatherValues(weatherValues);
                    weatherValuesEntity.setTime(time.substring(0,10));
                    weatherValuesEntity.setLocation(location);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(location);
                    stringBuilder.append(time);
                    weatherValuesEntity.setId(String.valueOf(stringBuilder));
                    dailyForecastRepository.save(weatherValuesEntity);
                }
            }
            else
            {
                logger.warn("Received unsuccessful Status code: {}" + responseEntity.getStatusCode());
            }
        }catch (FeignException e)
        {
            logger.error("Failed to fetch data from api: {}" + e.getMessage());
            throw new RuntimeException("Failed to fetch data from api");
        }catch (DataAccessException e)
        {
            logger.error("Failed to fetch data from database: {}"+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        catch (Exception e)
        {
            logger.error("Unexpected error occurred: {}"+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<?> getForecastForLocationByDate(String location, Date time) throws JsonProcessingException {
        getDailyForecastFromAPI(location);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String timestampAsString = dateFormat.format(time);
        Optional<WeatherValuesEntity> weatherValuesEntityOptional = Optional.ofNullable(dailyForecastRepository.findByLocationAndTime(location, timestampAsString));
        if(weatherValuesEntityOptional.isPresent())
        {
            WeatherValuesEntity weatherValuesEntity = weatherValuesEntityOptional.get();
            return ResponseEntity.ok(entityToBeanMapping.mapEntityToBeanForLocationForecast(weatherValuesEntity));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
        }
    }
}
