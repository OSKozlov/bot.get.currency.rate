package com.telegram.ccyrate.bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Command interface for handling telegram-bot commands
 */
public interface Command {

    /**
     * Main method which executing command logic
     *
     * @param update
     */
    void execute(Update update);
}
