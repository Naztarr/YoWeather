package com.naz.yoWeather.service;

import com.naz.yoWeather.exception.YoWeatherException;
import com.naz.yoWeather.payload.LocationResponse;
import com.naz.yoWeather.payload.WeatherApiResponse;
import com.naz.yoWeather.payload.YoWeatherResponse;
import com.naz.yoWeather.util.WeatherApiEndpoints;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
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

    public YoWeatherResponse getCurrentWeather(String firstName, HttpServletRequest request){
//        String clientIp = request.getHeader("X-Forwarded-For");
//        if(clientIp == null){
//            clientIp = request.getRemoteAddr();
//        }
        String clientIp = request.getRemoteAddr();

        HttpEntity<Object> entity = new HttpEntity<>(getWeatherApiHeader());
        ResponseEntity<LocationResponse> response = rest.exchange(
                WeatherApiEndpoints.TIME_ZONE(clientIp, API_KEY),
                HttpMethod.GET, entity,
                LocationResponse.class);
        log.debug(API_KEY);
        log.debug(String.valueOf(response));


        if(response.getStatusCode().is2xxSuccessful()){
            String city = response.getBody().getRegion();
            ResponseEntity<WeatherApiResponse> weatherResponse = rest.exchange(
                    WeatherApiEndpoints.CURRENT(city, API_KEY), HttpMethod.GET, entity, WeatherApiResponse.class
            );
            if(weatherResponse.getStatusCode().is2xxSuccessful()){
                Double temperature = weatherResponse.getBody().getCurrent().getTempC();
                String region = weatherResponse.getBody().getLocation().getRegion();
                String country = weatherResponse.getBody().getLocation().getCountry();
                YoWeatherResponse yoResponse = new YoWeatherResponse();
                yoResponse.setClientIp(clientIp);
                yoResponse.setLocation(region);
                yoResponse.setGreeting(String.format("Hello, %s!, the temperature is %s degrees Celsius in %s, %s.",
                        firstName, temperature, region, country));

                return yoResponse;
            } else {
                throw new YoWeatherException("Unable to process current weather");
            }
        } else {
            throw new YoWeatherException("unable to process location at the moment");
        }
    }
}
