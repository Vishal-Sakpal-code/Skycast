package com.spring.project.skycast.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.skycast.bean.Dailylocationdata;
import com.spring.project.skycast.service.DailyForecastService;
import com.spring.project.skycast.service.DailyWeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DailyForecastService dailyForecastService;

    @MockBean
    private DailyWeatherService dailyWeatherService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetDailyWeatherDataByLocation() throws Exception {
        Dailylocationdata mockData = new Dailylocationdata();
        mockData.setHumidity(31);
        mockData.setPrecipitationProbability(0);
        mockData.setRainIntensity(0);
        mockData.setSnowIntensity(0);
        mockData.setTemperature(31.88);
        mockData.setUvHealthConcern(2);
        mockData.setUvIndex(5);
        mockData.setVisibility(16);
        mockData.setWindDirection(282.5);
        mockData.setWindSpeed(2);
        // Mock the service response
        when(dailyWeatherService.fetchDataByLocation(anyString())).thenReturn(mockData);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/api/weather/daily/{location}", "someLocation"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.humidity").value(31))
                .andExpect(jsonPath("$.precipitationProbability").value(0))
                .andExpect(jsonPath("$.rainIntensity").value(0))
                .andExpect(jsonPath("$.snowIntensity").value(0))
                .andExpect(jsonPath("$.temperature").value(31.88))
                .andExpect(jsonPath("$.uvHealthConcern").value(2))
                .andExpect(jsonPath("$.uvIndex").value(5))
                .andExpect(jsonPath("$.visibility").value(16))
                .andExpect(jsonPath("$.windDirection").value(282.5))
                .andExpect(jsonPath("$.windSpeed").value(2));
    }

    @Test
    void testGetForecastForLocationByDate() throws Exception {
        // Mock the service response
        when(dailyForecastService.getForecastForLocationByDate(anyString(), any(Date.class))).thenReturn(ResponseEntity.ok().build());

        // Perform the GET request and verify the response
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());

        mockMvc.perform(get("/api/weather/forecast/{location}/{date}", "mumbai", date))
                .andExpect(status().isOk());
    }

    @Test
    void testGetForecastForLocationByDate_ErrorProcessingJSON() throws Exception {
        // Mock the service to throw JsonProcessingException
        when(dailyForecastService.getForecastForLocationByDate(anyString(), any(Date.class)))
                .thenThrow(new JsonProcessingException("Error processing JSON") {});

        // Perform the GET request and verify the response
        mockMvc.perform(get("/api/weather/forecast/{location}/{date}", "mumbai", "2022-01-30"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error processing JSON"));
    }

}
