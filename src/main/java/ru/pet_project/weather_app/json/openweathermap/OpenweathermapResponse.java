package ru.pet_project.weather_app.json.openweathermap;

import lombok.Data;

import java.util.List;

@Data
public class OpenweathermapResponse {
        private String cod;
        private Integer message;
        private Integer cnt;
        private List<Forecast> list;
        private City city;
}
