package ru.pet_project.weather_app.service;

import reactor.core.publisher.Mono;
import ru.pet_project.weather_app.json.openweathermap.OpenweathermapResponse;
import ru.pet_project.weather_app.model.City;

public interface WeatherSupplier {
    public Mono<OpenweathermapResponse> getWeather(City city);
}
