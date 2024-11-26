package ru.pet_project.weather_app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pet_project.weather_app.model.City;
import ru.pet_project.weather_app.model.Weather;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class WeathermapClient implements WeatherSupplier {

    private final WebClient webClient;
    @Override
    public Weather getWeather(City city) {
        return null;
    }
}
