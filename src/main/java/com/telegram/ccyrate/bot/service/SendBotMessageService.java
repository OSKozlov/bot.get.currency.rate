package com.telegram.ccyrate.bot.service;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

/**
 * Service for sending messages via telegram-bot.
 */
public interface SendBotMessageService {

    /**
     * Send message via telegram bot.
     *
     * @param chatId provided chatId in which messages would be sent.
     * @param message provided message to be sent.
     */
    void sendMessage(String chatId, String message);

    /**
     * Send message with inline keyboard via telegram bot.
     *
     * @param chatId provided chatId in which messages would be sent.
     * @param message provided message to be sent.
     * @param inlineKeyboardMarkup provided keyBoard for telegram bot.
     */
    void sendInlineKeyBoardMessage(String chatId, String message, InlineKeyboardMarkup inlineKeyboardMarkup);

    /**
     * Send message with inline keyboard via telegram bot.
     *
     * @param chatId provided chatId in which messages would be sent.
     * @param replyKeyboardMarkup provided keyBoard for telegram bot.
     */
    void sendReplyKeyBoardMessage(String chatId, String message, ReplyKeyboardMarkup replyKeyboardMarkup);

    /**
     * Send message via telegram bot.
     *
     * @param callbackQuery provided incoming callback query from a callback button in an inline keyboard.
     */
    void sendCallbackMessage(CallbackQuery callbackQuery);

    /**
     * Send create menu message via telegram bot
     *
     * @param commandList - menu commands list for telegram menu.
     */
    void sendCreateMenuMessage(List<BotCommand> commandList);

}
