package ru.pet_project.weather_app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.pet_project.weather_app.model.City;
import ru.pet_project.weather_app.service.WeathermapClient;

@SpringBootTest
public class WeathermapClientTest {
    @Autowired
    WeathermapClient weathermapClient;

    @Test
    public void getWeather_shouldReturnWeather() {
        weathermapClient.getWeather(new City("Chelyabinsk"));
    }
}
