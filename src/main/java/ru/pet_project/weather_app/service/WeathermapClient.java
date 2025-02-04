package ru.pet_project.weather_app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.pet_project.weather_app.json.openweathermap.OpenweathermapResponse;

public class WeathermapClient {

    private final WebClient webClient;

    @Value("${openweathermap.api-key}")
    private String apiKey;

    public WeathermapClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<OpenweathermapResponse> getWeather(Long wmCityId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("id", wmCityId.toString())
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .bodyToMono(OpenweathermapResponse.class);
    }
}
