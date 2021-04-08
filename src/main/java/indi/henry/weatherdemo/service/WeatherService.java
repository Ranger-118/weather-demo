package indi.henry.weatherdemo.service;

import java.util.List;

import indi.henry.weatherdemo.entity.Weather;

public interface WeatherService {

    public List<Weather> getWeatherAllInfo();
    
    public Weather getWeatherInfo(String city);

    public Weather addWeatherCity(String city);

    public Weather modifyWeatherInfo(Weather info);

    public Boolean deleteWeatherCity(String city);
}
