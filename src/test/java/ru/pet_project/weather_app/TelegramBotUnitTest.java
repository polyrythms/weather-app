package ru.pet_project.weather_app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.pet_project.weather_app.config.TelegramBotConfig;
import ru.pet_project.weather_app.model.City;
import ru.pet_project.weather_app.service.TelegramBot;
import ru.pet_project.weather_app.service.WeathermapClient;

public class TelegramBotUnitTest {

    private final TelegramBotConfig telegramBotConfig = new TelegramBotConfig();
    @Mock
    private WeathermapClient weathermapClient;
    private final String city = "Челябинск";
    private final TelegramBot telegramBot = new TelegramBot(telegramBotConfig, weathermapClient);


    @Test
    public void validateLocationMessage_shouldValidateAndTrimMessage_whenPrefixAtFirst() {
        String messageText = TelegramBot.LOCATION_MESSAGE_PREFIX + city;
        String result = telegramBot.validateLocationMessage(messageText);
        Assertions.assertEquals(city, result);
    }
    @Test
    public void parseMessageTextToCity_shouldParseJustCity() {
        City cityModel = telegramBot.parseMessageTextToCity(city);
        Assertions.assertEquals(new City(city, null, null), cityModel);
    }
    @Test
    public void parseMessageTextToCity_shouldParseStringWithSeparator() {
        City cityModel = telegramBot.parseMessageTextToCity(city + TelegramBot.SEPARATOR + city + TelegramBot.SEPARATOR + city);
        Assertions.assertEquals(new City(city, city, city), cityModel);
    }
    @Test
    public void parseMessageTextToCity_shouldReturnNull_whileTooManySeparators() {
        City cityModel = telegramBot.parseMessageTextToCity(city + TelegramBot.SEPARATOR + city + TelegramBot.SEPARATOR + city +TelegramBot.SEPARATOR + city);
        Assertions.assertNull(cityModel);
    }
}
