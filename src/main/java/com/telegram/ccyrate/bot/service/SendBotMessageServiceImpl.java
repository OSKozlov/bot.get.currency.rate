package com.telegram.ccyrate.bot.service;

import com.telegram.ccyrate.bot.CcyRateTelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final Logger logger = LoggerFactory.getLogger(SendBotMessageServiceImpl.class);

    private final CcyRateTelegramBot ccyRateTelegramBot;

    @Autowired
    public SendBotMessageServiceImpl(CcyRateTelegramBot ccyRateTelegramBot) {
        this.ccyRateTelegramBot = ccyRateTelegramBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            ccyRateTelegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Error has occured while sending a message to telegram chat...");
        }
    }

    @Override
    public void sendInlineKeyBoardMessage(String chatId, String message) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("UAH/USD");
        inlineKeyboardButton1.setCallbackData("Button \"UAH/USD\" has been tapped");
        inlineKeyboardButton2.setText("UAH/EUR");
        inlineKeyboardButton2.setCallbackData("Button \"UAH/EUR\" has been tapped");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            ccyRateTelegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Error has occured while sending a message to telegram chat...");
        }
    }

    @Override
    public void sendCallbackMessage(CallbackQuery callbackQuery) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(callbackQuery.getData());
        sendMessage.setChatId(callbackQuery.getMessage().getChatId());
        try {
            ccyRateTelegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Error has occured while sending a callback message...");
        }
    }
}
