package indi.henry.weatherdemo.controller;

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
    public ResponseEntity<Weather> get(@PathVariable String city) {
        Weather result = weatherService.getWeatherInfo(city);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Weather> post(@RequestBody String city) {
        
        return null;
    }

    @PutMapping
    public ResponseEntity<Weather> put(@RequestBody Weather modifiedItem) {

        return null;
    }

    @DeleteMapping
    public ResponseEntity<Weather> put(@PathVariable String city) {

        return null;
    }
}
