package com.telegram.ccyrate.bot.command;

import com.telegram.ccyrate.bot.service.SendBotMessageService;
import com.telegram.ccyrate.bot.state.BotStateMgr;
import com.telegram.ccyrate.bot.state.StopState;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Implementation of Stop command {@link Command}
 */
public class StopCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String STOP_MESSAGE = "Bot deactivated...";

    public StopCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);

        BotStateMgr.getInstance().setState(new StopState());
    }
}