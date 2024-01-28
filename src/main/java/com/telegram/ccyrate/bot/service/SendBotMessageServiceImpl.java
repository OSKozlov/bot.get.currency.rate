package com.telegram.ccyrate.bot.service;

import com.telegram.ccyrate.bot.CcyRateTelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

/**
 * Implementation of {@link SendBotMessageService} interface.
 */
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
        send(sendMessage);
    }

    @Override
    public void sendInlineKeyBoardMessage(String chatId, String message, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        send(sendMessage);
    }

    @Override
    public void sendReplyKeyBoardMessage(String chatId, String message, ReplyKeyboardMarkup replyKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        send(sendMessage);
    }

    @Override
    public void sendCallbackMessage(CallbackQuery callbackQuery) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(callbackQuery.getData());
        sendMessage.setChatId(callbackQuery.getMessage().getChatId());
        send(sendMessage);
    }

    @Override
    public void sendCreateMenuMessage(List<BotCommand> commandList) {
        try {
            ccyRateTelegramBot.execute(new SetMyCommands(commandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            logger.error("Error has occured while sending a message to telegram chat...");
        }
    }

    private void send(SendMessage sendMessage) {
        try {
            ccyRateTelegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Error has occured while sending a message to telegram chat...");
        }
    }
}
