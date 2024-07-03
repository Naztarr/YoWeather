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
public class LocationResponse {
    private String name;
    private String region;
    private String country;
    @JsonProperty(value = "lat")
    private Double latitude;
    @JsonProperty(value = "lon")
    private Double longitude;
    @JsonProperty(value = "tz_id")
    private String tzId;
    @JsonProperty(value = "localtime_epoch")
    private Integer localTimeEpoch;
    @JsonProperty(value = "localtime")
    private String localTime;
}
