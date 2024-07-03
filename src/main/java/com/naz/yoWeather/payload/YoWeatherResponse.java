package com.naz.yoWeather.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class YoWeatherResponse {
    @JsonProperty(value = "client_ip")
    private String clientIp;
    private String location;
    private String greeting;
}
