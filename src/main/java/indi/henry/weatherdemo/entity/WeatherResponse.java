package indi.henry.weatherdemo.entity;

import lombok.Data;

@Data
public class WeatherResponse {
    
    private String city;

    private String updatedTime;

    private String weather;

    private String temperature;

    private String wind;

}
