package com.naz.yoWeather.util;

public class WeatherApiEndpoints {
    private static final String BASE_URL = "http://api.weatherapi.com/v1";

    public static String TIME_ZONE(String clientIp, String apiKey){
        return BASE_URL + "/timezone.json?q=%s&key=%s".formatted(clientIp, apiKey);
    }
    public static String CURRENT(String city, String apiKey){
        return BASE_URL + "/current.json?q=%s&key=%s".formatted(city, apiKey);
    }
}
