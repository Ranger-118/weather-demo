package indi.henry.weatherdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import indi.henry.weatherdemo.entity.Weather;

public interface WeatherRepository extends JpaRepository<Weather, String> {
    
}
