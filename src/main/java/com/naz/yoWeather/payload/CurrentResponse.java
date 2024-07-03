package com.naz.yoWeather.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CurrentResponse {
    @JsonProperty(value = "last_updated")
    private String lastUpdated;
    @JsonProperty(value = "temp_c")
    private Double tempC;
    @JsonProperty(value = "temp_f")
    private Double tempF;
    private ConditionResponse condition;
}
