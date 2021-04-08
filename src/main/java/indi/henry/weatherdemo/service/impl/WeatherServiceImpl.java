package indi.henry.weatherdemo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import indi.henry.weatherdemo.entity.Weather;
import indi.henry.weatherdemo.exception.AppException;
import indi.henry.weatherdemo.repository.WeatherRepository;
import indi.henry.weatherdemo.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public List<Weather> getWeatherAllInfo() {
        List<Weather> result = weatherRepository.findAll();
        return result;
    }

    @Override
    public Weather getWeatherInfo(String city) {
        Optional<Weather> result = weatherRepository.findById(city);
        result.orElseThrow(() -> new AppException("Cannot find the city"));
        return result.get();
    }

    @Override
    public Weather addWeatherCity(String city) {
        Weather item = weatherRepository.findById(city).orElseGet(null);;
        if (!ObjectUtils.isEmpty(item)) {
            item.setValid(true);
            return weatherRepository.saveAndFlush(item);
        } else return null;
    }

    @Override
    public Weather modifyWeatherInfo(Weather info) {
        return weatherRepository.saveAndFlush(info);
    }

    @Override
    public Boolean deleteWeatherCity(String city) {
        weatherRepository.deleteById(city);
        return true;
    }
    
}
