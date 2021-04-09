package indi.henry.weatherdemo.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Main {

    private BigDecimal temp;

    private Long pressure;

    private Long humidity;

    private BigDecimal temp_min;

    private BigDecimal temp_max;
}
