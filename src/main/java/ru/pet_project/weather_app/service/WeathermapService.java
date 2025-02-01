package ru.pet_project.weather_app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pet_project.weather_app.entity.WeathermapCityEntity;
import ru.pet_project.weather_app.model.City;
import ru.pet_project.weather_app.model.Weather;
import ru.pet_project.weather_app.repository.WeathermapCityRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WeathermapService {
    private final WeathermapClient weathermapClient;
    private final WeathermapCityRepository weathermapCityRepository;

    public List<WeathermapCityEntity> validateCity(City city) {
        if (city.getCity() != null && city.getCountry() == null && city.getState() == null) {
            return weathermapCityRepository.findAllByCityEntityCity(city.getCity());
        } else if ((city.getCity() != null && city.getCountry() != null && city.getState() == null)) {
            return weathermapCityRepository.findAllByCityEntityCityAndCountry(city.getCity(), city.getCountry());
        } else if (city.getCity() != null && city.getCountry() != null && city.getState() != null) {
            return weathermapCityRepository.findAllByCityEntityCityAndCountryAndState(city.getCity(), city.getCountry(), city.getState());
        } else {
            throw new IllegalArgumentException("validateCity(City city) error");
        }
    }

    public List<Weather> getWeather(String wmCityId) {
        var result = weathermapClient.getWeather(wmCityId);
        return null;
    }
}
