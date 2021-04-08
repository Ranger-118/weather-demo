package indi.henry.weatherdemo.service;

import indi.henry.weatherdemo.entity.Weather;

public interface WeatherService {
    
    public Weather getWeatherInfo(String city);

    public Boolean addWeatherCity(String city);

    public Weather modifyWeatherInfo(Weather info);

    public Boolean deleteWeatherCity(String city);
}
