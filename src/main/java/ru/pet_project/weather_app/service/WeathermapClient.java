package ru.pet_project.weather_app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.pet_project.weather_app.model.City;
import ru.pet_project.weather_app.model.Weather;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeathermapClient implements WeatherSupplier {

    private final WebClient webClient;
    @Value("${openweathermap.api-key}")
    private String apiKey;
    @Value("${openweathermap.base-url}")
    private String baseUrl;

    public WeathermapClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Weather getWeather(City city) {
        String url = String.format("%s?q=%s&appid=%s&units=metric", baseUrl, city, apiKey);
        webClient.baseUrl;
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather")
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .bodyToMono(String.class);
        return null;
    }
}
