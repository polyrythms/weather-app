package ru.pet_project.weather_app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pet_project.weather_app.entity.WeathermapCityEntity;
import ru.pet_project.weather_app.model.City;
import ru.pet_project.weather_app.model.Weather;
import ru.pet_project.weather_app.repository.WeathermapCityRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class WeathermapService implements WeatherSupplier {
    private final WeathermapClient weathermapClient;
    private final WeathermapCityRepository weathermapCityRepository;


    @Override
    public List<Weather> getWeather(City city) {
        List<WeathermapCityEntity> cities = weathermapCityRepository.findByCity(city.getCity());

        weathermapClient.getWeather(cities.getFirst());
        return null;
    }
}
