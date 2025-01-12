package ru.pet_project.weather_app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.pet_project.weather_app.entity.WeathermapCityEntity;
import ru.pet_project.weather_app.json.openweathermap.OpenweathermapResponse;
import ru.pet_project.weather_app.model.City;

public class WeathermapClient {

    private final WebClient webClient;
    @Value("${openweathermap.api-key}")
    private String apiKey;

    public WeathermapClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<OpenweathermapResponse> getWeather(WeathermapCityEntity wmCity) {
        return webClient.get()
               .uri(uriBuilder -> uriBuilder
                       .queryParam("q", wmCity.getCity())
                       .queryParam("appid", apiKey)
                       .queryParam("units", "metric")
                       .build())
               .retrieve()
               .bodyToMono(OpenweathermapResponse.class);
    }
}
