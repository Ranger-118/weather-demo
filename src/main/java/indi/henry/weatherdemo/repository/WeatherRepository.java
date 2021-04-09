package indi.henry.weatherdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import indi.henry.weatherdemo.entity.CityEntity;

public interface WeatherRepository extends JpaRepository<CityEntity, String> {
    
}
