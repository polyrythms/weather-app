package ru.pet_project.weather_app.json.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Main {
    private Double temp;
    @JsonProperty("feels_like")
    private Double feelsLike;
    @JsonProperty("temp_min")
    private Double tempMin;
    @JsonProperty("temp_max")
    private Double tempMax;
    private Integer pressure;
    @JsonProperty("sea_level")
    private Integer seaLevel;
    @JsonProperty("grnd_level")
    private Integer grndLevel;
    private Integer humidity;
    @JsonProperty("temp_kf")
    private Double tempKf;
}
