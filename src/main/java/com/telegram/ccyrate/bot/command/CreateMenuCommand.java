package com.telegram.ccyrate.bot.command;

import com.telegram.ccyrate.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

import static com.telegram.ccyrate.bot.command.CommandName.*;
import static com.telegram.ccyrate.bot.command.CommandName.HELP;

public class CreateMenuCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public CreateMenuCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        List<BotCommand> commandList = new ArrayList<>();
        commandList.add(new BotCommand(START.getCommandName(), "start bot"));
        commandList.add(new BotCommand(STOP.getCommandName(), "stop bot"));
        commandList.add(new BotCommand(HELP.getCommandName(), "command description"));

        sendBotMessageService.sendCreateMenuMessage(commandList);
    }
}
