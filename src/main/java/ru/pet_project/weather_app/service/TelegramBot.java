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
                    OpenweathermapResponse response = weathermapClient.getWeather(new City("Chelyabinsk")).block();
                    assert response != null;
                    JSONObject jsonObject = new JSONObject(response);
                    String s = jsonObject.toString(4);
                    String weatherMessage = s.substring(0, Math.min(s.length(), 4000));
                    sendMessage(chatId, weatherMessage);
                }
                case "/description" -> sendMessage(chatId, "description");
                default -> {
                }
            }
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = "Hi, " + name + ", nice to meet you!" + "\n" +
                "Enter the currency whose official exchange rate" + "\n" +
                "you want to know in relation to BYN." + "\n" +
                "For example: USD";
        sendMessage(chatId, answer);
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
