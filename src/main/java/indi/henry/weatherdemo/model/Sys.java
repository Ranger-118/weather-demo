package indi.henry.weatherdemo.model;

import lombok.Data;

@Data
public class Sys {
    
    private Long type;

    private Long id;

    private Long message;

    private String country;

    private Long sunrise;

    private Long sunset;
}
