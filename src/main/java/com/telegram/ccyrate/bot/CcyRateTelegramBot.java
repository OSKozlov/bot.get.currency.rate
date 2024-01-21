package com.telegram.ccyrate.bot;

import com.telegram.ccyrate.bot.command.CommandContainer;
import com.telegram.ccyrate.bot.service.SendBotMessageServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.telegram.ccyrate.bot.config.BotConfig;

import static com.telegram.ccyrate.bot.command.CommandName.*;

/**
 * This class represents telegram bot - https://t.me/CcyRateUABot
 */
@Component
@AllArgsConstructor
public class CcyRateTelegramBot extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(CcyRateTelegramBot.class);

    private static final String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    private BotConfig botConfig;

    public CcyRateTelegramBot() {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));

        botConfig = ApplicationContextProvider.getApplicationContext().getBean(BotConfig.class);

        commandContainer.retrieveCommand(CREATEMENU.getCommandName()).execute(null);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText().trim();
            if (messageText.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = messageText.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
                logger.info(" Command " + messageText + " performed...");
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        } else if (update.hasCallbackQuery()) {
            commandContainer.retrieveCommand(CALLBACK.getCommandName()).execute(update);
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}
