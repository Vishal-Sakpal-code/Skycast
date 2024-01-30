package com.spring.project.skycast.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "dailyforecast", url = "https://api.tomorrow.io/v4/weather")
public interface DailyForecast {

    @GetMapping("/forecast")
    public ResponseEntity<String> getdailyForecast(@RequestParam("location") String location, @RequestParam("apikey") String apiKey, @RequestParam("timesteps") String timeperiod);
}
