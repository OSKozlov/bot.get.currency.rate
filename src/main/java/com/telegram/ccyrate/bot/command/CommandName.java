package com.telegram.ccyrate.bot.command;

/**
 * Enumeration for {@link Command}'s
 */
public enum CommandName {

    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO("/no"),
    SHOW("/show"),
    CALLBACK("/callback"),
    CREATEMENU("/createmenu");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
