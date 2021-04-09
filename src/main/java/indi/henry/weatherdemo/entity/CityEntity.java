package indi.henry.weatherdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * City list entity
 * 
 * @author Henry Hu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CITY_LIST")
public class CityEntity {
    
    @Id
    private String city;

}
