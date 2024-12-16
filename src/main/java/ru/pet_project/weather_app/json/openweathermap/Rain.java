package ru.pet_project.weather_app.json.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rain {
    @JsonProperty("3h")
    private double threeHour;
}
