package com.telegram.ccyrate.bot.service;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);

    void sendInlineKeyBoardMessage(String chatId, String message, InlineKeyboardMarkup inlineKeyboardMarkup);

    void sendCallbackMessage(CallbackQuery callbackQuery);

    void sendCreateMenuMessage(List<BotCommand> commandList);

}
