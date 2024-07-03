package com.naz.yoWeather.payload;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConditionResponse {
    private String text;
    private Integer code;
}
