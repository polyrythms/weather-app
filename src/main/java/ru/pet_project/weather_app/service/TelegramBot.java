package ru.pet_project.weather_app.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pet_project.weather_app.config.TelegramBotConfig;
import ru.pet_project.weather_app.json.openweathermap.OpenweathermapResponse;
import ru.pet_project.weather_app.model.City;

import java.util.List;

@Service
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotConfig config;
    private final WeathermapClient weathermapClient;
    public static final String LOCATION_MESSAGE_PREFIX = "Локация: ";
    public static final String SEPARATOR = ", ";

    public TelegramBot(TelegramBotConfig config, WeathermapClient weathermapClient) {
        super(config.getBotToken());
        this.config = config;
        this.weathermapClient = weathermapClient;
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
                    OpenweathermapResponse response = weathermapClient.getWeather("1508291").block();
                    assert response != null;
                    JSONObject jsonObject = new JSONObject(response);
                    String s = jsonObject.toString(4);
                    String weatherMessage = s.substring(0, Math.min(s.length(), 4000));
                    sendMessage(chatId, weatherMessage);
                }
                case "/description" -> sendMessage(chatId, "description");
                default -> {
                    if (validateLocationMessage(messageText) != null) {
                        City city = parseMessageTextToCity(messageText);
                        sendMessage(chatId, String.valueOf(city));
                    }
                }
            }
        }
    }

    public City parseMessageTextToCity(String messageText) {
        String[] names = messageText.split(SEPARATOR);
        if (names.length == 0 || names.length > 3)
            return null;
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
            System.out.println("a");
        }
    }
}
