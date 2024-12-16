package ru.pet_project.weather_app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.pet_project.weather_app.model.City;
import ru.pet_project.weather_app.model.Weather;

public class WeathermapClient implements WeatherSupplier {

    private final WebClient webClient;
    @Value("${openweathermap.api-key}")
    private String apiKey;

    public WeathermapClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Weather getWeather(City city) {
        Mono<String> response = webClient.get()
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
