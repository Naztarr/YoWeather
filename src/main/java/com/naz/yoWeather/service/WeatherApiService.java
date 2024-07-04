package com.naz.yoWeather.service;

import com.naz.yoWeather.exception.YoWeatherException;
import com.naz.yoWeather.payload.LocationResponse;
import com.naz.yoWeather.payload.WeatherApiResponse;
import com.naz.yoWeather.payload.YoWeatherResponse;
import com.naz.yoWeather.util.WeatherApiEndpoints;
import jakarta.servlet.http.HttpServletRequest;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@Slf4j
@ToString
@Service
public class WeatherApiService {
    private final RestTemplate rest;
    @Value("${yoWeather.weatherApi.api-key}")
    private String API_KEY;

    public WeatherApiService(RestTemplate rest) {
        this.rest = rest;
    }

    public HttpHeaders getWeatherApiHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public YoWeatherResponse getCurrentWeather(String visitorName, HttpServletRequest request){
        String clientIp = request.getHeader("X-Forwarded-For");
        if(clientIp == null){
            clientIp = request.getRemoteAddr();
        }

        HttpEntity<Object> entity = new HttpEntity<>(getWeatherApiHeader());
            ResponseEntity<WeatherApiResponse> weatherResponse = rest.exchange(
                    WeatherApiEndpoints.CURRENT(clientIp, API_KEY), HttpMethod.GET, entity, WeatherApiResponse.class
            );
            log.info(String.valueOf(weatherResponse.getBody()));
            if(weatherResponse.getStatusCode().is2xxSuccessful()){
                Double temperature = weatherResponse.getBody().getCurrent().getTempC();
                String region = weatherResponse.getBody().getLocation().getRegion();
                String country = weatherResponse.getBody().getLocation().getCountry();
                YoWeatherResponse yoResponse = new YoWeatherResponse();
                yoResponse.setClientIp(clientIp);
                yoResponse.setLocation(region);
                yoResponse.setGreeting(String.format("Hello, %s!, the temperature is %s degrees Celsius in %s, %s.",
                        visitorName, temperature, region, country));

                return yoResponse;
            } else {
                throw new YoWeatherException("Unable to process current weather");
            }
        }
    }
