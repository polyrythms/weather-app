package ru.pet_project.weather_app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.pet_project.weather_app.entity.WeathermapCityEntity;
import ru.pet_project.weather_app.model.City;
import ru.pet_project.weather_app.service.WeathermapClient;

@SpringBootTest
public class WeathermapClientTest {
    @Autowired
    private WeathermapClient weathermapClient;

    @Test
    public void getWeather_shouldReturnWeather() {
        var result1 =weathermapClient.getWeather(1508291L).block();
        System.out.println(result1);
        var result2 = weathermapClient.getWeather(2968815L).block();
        System.out.println(result2);
    }
}
