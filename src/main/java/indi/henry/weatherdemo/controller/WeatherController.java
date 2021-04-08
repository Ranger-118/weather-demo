package indi.henry.weatherdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import indi.henry.weatherdemo.entity.Weather;
import indi.henry.weatherdemo.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<List<Weather>> get() {
        List<Weather> result = weatherService.getWeatherAllInfo();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/{city}")
    public ResponseEntity<Weather> get(@PathVariable String city) {
        Weather result = weatherService.getWeatherInfo(city);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Weather> post(@RequestBody String city) {
        Weather result = weatherService.addWeatherCity(city);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Weather> put(@RequestBody Weather modifiedItem) {
        Weather result = weatherService.modifyWeatherInfo(modifiedItem);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@PathVariable String city) {
        Boolean result = weatherService.deleteWeatherCity(city);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
