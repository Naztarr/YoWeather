package com.naz.yoWeather.controller;

import com.naz.yoWeather.payload.YoWeatherResponse;
import com.naz.yoWeather.service.WeatherApiService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WeatherController {
    private final WeatherApiService weatherApiService;

    @GetMapping("/hello")
    public YoWeatherResponse getCurrentWeather(@RequestParam String firstName, HttpServletRequest httpServletRequest){
        return weatherApiService.getCurrentWeather(firstName, httpServletRequest);
    }
}
