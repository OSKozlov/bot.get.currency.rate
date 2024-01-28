package com.telegram.ccyrate.bot.command;

import com.telegram.ccyrate.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.telegram.ccyrate.bot.command.CommandName.*;

/**
 * Implementation of Help command {@link Command}
 */
public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("✨<b>Available commands</b>✨\n\n"

                    + "<b>Start\\end work with the bot</b>\n"
                    + "%s - start working with me\n"
                    + "%s - suspend working with me\n"
                    + "%s - show rate for currency pair (for instance: /show USD)\n"
                    + "%s - get help working with me\n",
            START.getCommandName(), STOP.getCommandName(), SHOW.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
