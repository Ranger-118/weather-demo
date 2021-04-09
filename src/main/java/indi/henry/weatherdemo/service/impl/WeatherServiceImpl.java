package indi.henry.weatherdemo.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import indi.henry.weatherdemo.entity.CityEntity;
import indi.henry.weatherdemo.entity.WeatherResponse;
import indi.henry.weatherdemo.model.WeatherResult;
import indi.henry.weatherdemo.repository.WeatherRepository;
import indi.henry.weatherdemo.service.WeatherService;
import indi.henry.weatherdemo.util.AppUtil;

@Service
@Transactional
public class WeatherServiceImpl implements WeatherService {

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
        return result;
    }

    @Override
    public WeatherResponse getWeatherInfo(String city) throws RuntimeException {

        Map<String, String> params = new HashMap<>();
        params.put(AppUtil.CITY_PARAM, city);
        params.put(AppUtil.API_KEY_PARAM, apiKey);

        WeatherResult weatherResult = restTemplate.getForObject(weatherUrl, WeatherResult.class, params);
        String temperature = weatherResult.getMain().getTemp().min(BigDecimal.valueOf(32))
                .divide(BigDecimal.valueOf(1.8), 0).toString().concat(AppUtil.CENTIGRADE_SYMBOL);
        String updatedTime = new SimpleDateFormat(AppUtil.DATE_FORMAT)
                .format(Date.from(Instant.ofEpochSecond(weatherResult.getDt())));
        String weather = weatherResult.getWeather().get(0).getDescription();
        String wind = weatherResult.getWind().getSpeed().toString().concat(AppUtil.SPEED_UNIT);

        WeatherResponse result = new WeatherResponse();
        result.setCity(city);
        result.setTemperature(temperature);
        result.setUpdatedTime(updatedTime);
        result.setWeather(weather);
        result.setWind(wind);

        return result;
    }

    @Override
    public WeatherResponse addWeatherCity(String city) throws RuntimeException {
        WeatherResponse result = this.getWeatherInfo(city);
        CityEntity item = new CityEntity(result.getCity());
        item = weatherRepository.saveAndFlush(item);
        return result;
    }

    @Override
    public void deleteWeatherCity(String city) throws RuntimeException {
        weatherRepository.deleteById(city);
    }
}
