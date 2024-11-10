package com.telegram.ccyrate.bot.command;

import com.telegram.ccyrate.bot.service.CcyRateService;
import com.telegram.ccyrate.bot.service.SendBotMessageService;

import java.util.HashMap;
import java.util.Map;

import static com.telegram.ccyrate.bot.command.CommandName.*;

public class CommandContainer {

    private final Map<String, Command> commandMap = new HashMap<>();

    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandMap.put(START.getCommandName(), new StartCommand(sendBotMessageService));
        commandMap.put(STOP.getCommandName(), new StopCommand(sendBotMessageService));
        commandMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));
        commandMap.put(NO.getCommandName(), new NoCommand(sendBotMessageService));
        commandMap.put(SHOW.getCommandName(), new ShowCommand(sendBotMessageService));
        commandMap.put(CALLBACK.getCommandName(), new CallbackCommand(sendBotMessageService));
        commandMap.put(CREATEMENU.getCommandName(), new CreateMenuCommand(sendBotMessageService));
        commandMap.put(TOPCCYLIST.getCommandName(), new TopCcyListCommand(sendBotMessageService));

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
