package ru.pet_project.weather_app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pet_project.weather_app.model.City;
import ru.pet_project.weather_app.model.Weather;

import java.util.List;

@Service
@AllArgsConstructor
public class WeathermapService implements WeatherSupplier {
    private final WeathermapClient weathermapClient;
//    private final WeathermapCityRepository weathermapCityRepository;


    @Override
    public List<Weather> getWeather(City city) {
        weathermapClient.getWeather(city);
        return null;
    }
}
