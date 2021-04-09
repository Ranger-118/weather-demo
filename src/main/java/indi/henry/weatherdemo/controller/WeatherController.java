package indi.henry.weatherdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import indi.henry.weatherdemo.entity.CityEntity;
import indi.henry.weatherdemo.entity.WeatherResponse;
import indi.henry.weatherdemo.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    
    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<List<CityEntity>> get() {
        List<CityEntity> result = weatherService.getWeatherAllInfo();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponse> get(@PathVariable String city) throws RuntimeException {
        WeatherResponse result = weatherService.getWeatherInfo(city);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WeatherResponse> post(@RequestBody String city) throws RuntimeException {
        WeatherResponse result = weatherService.addWeatherCity(city);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{city}")
    public ResponseEntity<Boolean> delete(@PathVariable String city) {
        weatherService.deleteWeatherCity(city);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
