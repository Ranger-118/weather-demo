package indi.henry.weatherdemo.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import indi.henry.weatherdemo.entity.CityEntity;
import indi.henry.weatherdemo.model.WeatherResponse;
import indi.henry.weatherdemo.model.WeatherResult;
import indi.henry.weatherdemo.repository.WeatherRepository;
import indi.henry.weatherdemo.service.WeatherService;
import indi.henry.weatherdemo.util.AppConstants;

/**
 * The implementation for the weather services
 * 
 * @author Henry Hu
 */
@Service
@Transactional
public class WeatherServiceImpl implements WeatherService {

    private static final Logger appLog = LoggerFactory.getLogger(WeatherService.class);

    @Value("${weather-url}")
    private String weatherUrl;

    @Value("${weather-api-key}")
    private String apiKey;

    private WeatherRepository weatherRepository;

    private RestTemplate restTemplate;

    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository, RestTemplate restTemplate) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CityEntity> getWeatherAllInfo() {
        List<CityEntity> result = weatherRepository.findAll();
        appLog.info("City List: {}", result);
        return result;
    }

    /**
     * The method would call the weather public API for current weather information for target city by using Rest Template,
     * and abstract the requested fields to construct the weather response structure for output
     * 
     * @author Henry Hu
     */
    @Override
    public WeatherResponse getWeatherInfo(String city) throws RuntimeException {

        Map<String, String> params = new HashMap<>();
        params.put(AppConstants.CITY_PARAM, city);
        params.put(AppConstants.API_KEY_PARAM, apiKey);

        WeatherResult weatherResult = restTemplate.getForObject(weatherUrl, WeatherResult.class, params);

        appLog.info("Calling weather public API result: {}", weatherResult);

        String temperature = weatherResult.getMain().getTemp().min(BigDecimal.valueOf(32))
                .divide(BigDecimal.valueOf(1.8), 0, RoundingMode.HALF_UP).toString().concat(AppConstants.CENTIGRADE_SYMBOL);
        String updatedTime = new SimpleDateFormat(AppConstants.DATE_FORMAT)
                .format(Date.from(Instant.ofEpochSecond(weatherResult.getDt())));
        String weather = weatherResult.getWeather().get(0).getDescription();
        String wind = weatherResult.getWind().getSpeed().toString().concat(AppConstants.SPEED_UNIT);

        return WeatherResponse.builder()
                    .city(city)
                    .temperature(temperature)
                    .updatedTime(updatedTime)
                    .weather(weather)
                    .wind(wind)
                    .build();
    }

    /**
     * The method would call the weather public API for city verification,
     * if no issue it would add the city to the database
     * 
     * @author Henry Hu
     */
    @Override
    public WeatherResponse addWeatherCity(String city) throws RuntimeException {
        WeatherResponse result = this.getWeatherInfo(city);
        CityEntity item = new CityEntity(result.getCity());
        item = weatherRepository.saveAndFlush(item);

        appLog.info("City added: {}", item);
        return result;
    }

    @Override
    public void deleteWeatherCity(String city) throws RuntimeException {
        weatherRepository.deleteById(city);

        appLog.info("City deleted: {}", city);
    }
}
