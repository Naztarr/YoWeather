package com.naz.yoWeather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class YoWeatherExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(YoWeatherException.class)
    public ResponseEntity<String> handleYoException(YoWeatherException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
