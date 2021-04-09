package indi.henry.weatherdemo.model;

import lombok.Builder;
import lombok.Data;

/**
 * Weather information for the http response
 * 
 * @author Henry Hu
 */
@Data
@Builder
public class WeatherResponse {
    
    private String city;

    private String updatedTime;

    private String weather;

    private String temperature;

    private String wind;

}
