package com.telegram.ccyrate.bot.service;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);

    void sendInlineKeyBoardMessage(String chatId, String message);

    void sendCallbackMessage(CallbackQuery callbackQuery);

}
