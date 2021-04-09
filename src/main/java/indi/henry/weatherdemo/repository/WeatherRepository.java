package indi.henry.weatherdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import indi.henry.weatherdemo.entity.CityEntity;

/**
 * The JPA repository for the CityEntity
 * 
 * @author Henry Hu
 */
public interface WeatherRepository extends JpaRepository<CityEntity, String> {
    
}
