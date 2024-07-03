package com.naz.yoWeather.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WeatherApiResponse {
    private LocationResponse location;
    private CurrentResponse current;
}
