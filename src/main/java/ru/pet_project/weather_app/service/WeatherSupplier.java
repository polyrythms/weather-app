package ru.pet_project.weather_app.service;

import reactor.core.publisher.Mono;
import ru.pet_project.weather_app.json.openweathermap.OpenweathermapResponse;
import ru.pet_project.weather_app.model.City;
import ru.pet_project.weather_app.model.Weather;

import java.util.List;

public interface WeatherSupplier {
    public List<Weather> getWeather(City city);
}
