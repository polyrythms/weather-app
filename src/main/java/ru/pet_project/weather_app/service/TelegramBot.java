package ru.pet_project.weather_app.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pet_project.weather_app.config.TelegramBotConfig;
import ru.pet_project.weather_app.entity.WeathermapCityEntity;
import ru.pet_project.weather_app.model.City;
import ru.pet_project.weather_app.model.Weather;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotConfig config;
    public static final String LOCATION_MESSAGE_PREFIX = "Локация: ";
    public static final String SEPARATOR = ", ";
    public static final String LOCATION_NOT_FOUND = "Не нашли локацию";
    private final WeathermapService weathermapService;

    public TelegramBot(TelegramBotConfig config, WeathermapService weathermapService) {
        super(config.getBotToken());
        this.config = config;
        this.weathermapService = weathermapService;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }


    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().getText() != null) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            switch (messageText) {
                case "/weather" -> {
                    var entity = new WeathermapCityEntity();
                    entity.setWeathermapId(1508291L);
                    List<Weather> response = weathermapService.getWeather(entity);
                    assert response != null;
                    JSONObject jsonObject = new JSONObject(response);
                    String s = jsonObject.toString(4);
                    String weatherMessage = s.substring(0, Math.min(s.length(), 4000));
                    sendMessage(chatId, weatherMessage);
                }
                case "/description" -> sendMessage(chatId, "description");
                default -> {
                    String location = validateLocationMessage(messageText);
                    if (location != null) {
                        String answer;
                        City city = parseMessageTextToCity(location);
                        List<WeathermapCityEntity> wmCities = weathermapService.validateCity(city);
                        if (wmCities == null || wmCities.size() == 0) {
                            answer = LOCATION_NOT_FOUND;
                        } else if (wmCities.size() == 1) {
                            List<Weather> weather = weathermapService.getWeather(wmCities.get(0));
                            answer = "Погода для города " + wmCities.get(0).getCityEntity().toStringWithoutNullFields() + "\n" +
                                    weatherToMessage(weather);
                        } else {
                            answer = "Нашли такие города, выберите один однозначный: \n"
                                    + wmCities.stream().map(a -> a.getCityEntity().toStringWithoutNullFields()).collect(Collectors.joining("\n"));
                        }
                        sendMessage(chatId, answer);
                    }
                }
            }
        }
    }

    public City parseMessageTextToCity(String messageText) {
        String[] names = messageText.split(SEPARATOR);
        if (names.length == 0 || names.length > 3) return null;
        City city = new City();
        for (int i = 0; i < names.length; i++) {
            if (i == 0) {
                city.setCity(names[i]);
            } else if (i == 1) {
                city.setCountry(names[i]);
            } else {
                city.setState(names[i]);
            }
        }
        return city;
    }

    public String validateLocationMessage(String message) {
        if (!message.matches(LOCATION_MESSAGE_PREFIX + ".*")) {
            return null;
        }
        return message.replaceFirst(LOCATION_MESSAGE_PREFIX, "");
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

    public String weatherToMessage(List<Weather> weatherList) {
        String message = weatherList.get(0).getCity().toStringWithoutNullFields();
        for (Weather weather : weatherList) {
            message += "\n" + weather.getDate()
                    + "\n" + weather.getTemperature()
                    + "\n" + weather.getCloud().getDescription()
                    + "\n" + weather.getWind().getDirection().getDescriptionInRussian();
        }
        return message.substring(400);
    }
}
