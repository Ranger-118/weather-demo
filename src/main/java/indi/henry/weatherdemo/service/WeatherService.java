package indi.henry.weatherdemo.service;

import java.util.List;

import indi.henry.weatherdemo.entity.CityEntity;
import indi.henry.weatherdemo.entity.WeatherResponse;

public interface WeatherService {

    public List<CityEntity> getWeatherAllInfo();
    
    public WeatherResponse getWeatherInfo(String city) throws RuntimeException;

    public WeatherResponse addWeatherCity(String city) throws RuntimeException;

    public void deleteWeatherCity(String city);
}
