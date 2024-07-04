package com.naz.yoWeather.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "region")
    private String region;
    @JsonProperty(value = "country")
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
