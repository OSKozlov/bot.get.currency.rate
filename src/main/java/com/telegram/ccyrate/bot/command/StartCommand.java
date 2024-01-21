package com.telegram.ccyrate.bot.command;

import com.telegram.ccyrate.bot.service.SendBotMessageService;
import com.telegram.ccyrate.bot.state.BotStateMgr;
import com.telegram.ccyrate.bot.state.StartState;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {

    private static final String MESSAGE_START = "Hi, nice to meet you!" + "\n" +
            "Enter the currency whose official exchange rate" + "\n" +
            "you want to know in relation to UAH." + "\n" +
            "For example: /show USD";

    private final SendBotMessageService sendBotMessageService;

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), MESSAGE_START);

        BotStateMgr.getInstance().setState(new StartState());
    }
}
