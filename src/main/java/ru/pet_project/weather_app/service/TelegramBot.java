package ru.pet_project.weather_app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;

    public TelegramBot(String botToken) {
        super(botToken);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }


    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {
//        CurrencyModel currencyModel = new CurrencyModel();
//        String currency = "";
//
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            String messageText = update.getMessage().getText();
//            long chatId = update.getMessage().getChatId();
//
//            switch (messageText) {
//                case "/start":
//                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
//                    break;
//                default:
//                    try {
//                        currency = CurrencyService.getCurrencyRate(messageText, currencyModel);
//
//                    } catch (IOException e) {
//                        sendMessage(chatId, "We have not found such a currency." + "\n" +
//                                "Enter the currency whose official exchange rate" + "\n" +
//                                "you want to know in relation to BYN." + "\n" +
//                                "For example: USD");
//                    } catch (ParseException e) {
//                        throw new RuntimeException("Unable to parse date");
//                    }
//                    sendMessage(chatId, currency);
//            }
//        }

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
//        super.onUpdatesReceived(updates);
//    }
//
//    private void startCommandReceived(Long chatId, String name) {
//        String answer = "Hi, " + name + ", nice to meet you!" + "\n" +
//                "Enter the currency whose official exchange rate" + "\n" +
//                "you want to know in relation to BYN." + "\n" +
//                "For example: USD";
//        sendMessage(chatId, answer);
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }
    }

}
