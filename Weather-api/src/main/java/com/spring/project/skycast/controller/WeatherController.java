package com.spring.project.skycast.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.project.skycast.bean.Dailylocationdata;
import com.spring.project.skycast.service.DailyForecastService;
import com.spring.project.skycast.service.DailyWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class WeatherController {

    private final DailyForecastService dailyForecastService;
    private final DailyWeatherService dailyWeatherService;

    @Autowired
    public WeatherController(DailyForecastService dailyForecastService, DailyWeatherService dailyWeatherService) {
        this.dailyForecastService = dailyForecastService;
        this.dailyWeatherService = dailyWeatherService;
    }

    @GetMapping("/api/weather/daily/{location}")
    public Dailylocationdata getDailyWeatherDataByLocation(@PathVariable String location)
    {
        return dailyWeatherService.fetchDataByLocation(location);
    }

    @GetMapping("/api/weather/forecast/{location}/{date}")
    public ResponseEntity<?> getForecastForLocationByDate(@PathVariable String location, @PathVariable String date) throws JsonProcessingException, ParseException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            return dailyForecastService.getForecastForLocationByDate(location, parsedDate);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing JSON");
        }
    }
}
