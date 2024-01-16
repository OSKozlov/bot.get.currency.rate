package com.telegram.ccyrate.bot.command;

import com.telegram.ccyrate.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CallbackCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public CallbackCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendCallbackMessage(update.getCallbackQuery());
    }
}
