package com.spring.project.skycast.Feign;

import com.spring.project.skycast.bean.DailyCityWeather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "tomorrowIoClient", url = "https://api.tomorrow.io/v4/weather")
public interface DailyWeather {

    @GetMapping("/realtime")
    DailyCityWeather getWeatherData(@RequestParam("location") String location, @RequestParam("apikey") String apiKey);
}
