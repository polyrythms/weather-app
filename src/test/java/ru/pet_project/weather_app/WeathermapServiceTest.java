package ru.pet_project.weather_app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.pet_project.weather_app.model.City;
import ru.pet_project.weather_app.service.WeathermapService;

@SpringBootTest
public class WeathermapServiceTest {
    @Autowired
    private WeathermapService weathermapService;
    @Test
    public void validateCity_shouldReturnEntity_whenCallRussianCity() {
        var result = weathermapService.validateCity(new City("Челябинск", null, null));
        Assertions.assertTrue(result.size() > 0);
        Assertions.assertEquals(result.get(0).getWeathermapId(), 1508291L);

    }


}
