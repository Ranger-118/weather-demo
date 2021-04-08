package indi.henry.weatherdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Weather {
    
    @Id
    private String city;

    private String updatedTime;

    private String weather;

    private String temperature;

    private String wind;

    private Boolean enabled;
}
