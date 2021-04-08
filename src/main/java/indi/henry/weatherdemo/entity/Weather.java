package indi.henry.weatherdemo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity(name = "APP_WEATHER")
public class Weather {
    
    @Id
    private String city;

    private String updatedTime;

    private String weather;

    private String temperature;

    private String wind;

    private Boolean valid;
}
