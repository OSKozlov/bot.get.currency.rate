package com.telegram.ccyrate.bot.command;

import com.telegram.ccyrate.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of no command {@link Command}
 */
public class NoCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String NO_MESSAGE = "I support commands starting with a slash(/).\n"
            + "To see a list of commands, enter /help";

    public NoCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                NO_MESSAGE);

        /*sendBotMessageService.sendReplyKeyBoardMessage(update.getMessage().getChatId().toString(),
                NO_MESSAGE, createReplyKeyboard());*/
    }

    private ReplyKeyboardMarkup createReplyKeyboard() {

        // Create keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Create list of keyboard
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

        // First row of keyboard
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Append keyboard
        KeyboardButton startBtn = new KeyboardButton();
        startBtn.setText("/start");
        KeyboardButton showBtn = new KeyboardButton();
        showBtn.setText("/topccylist");
        keyboardFirstRow.add(startBtn);
        keyboardFirstRow.add(showBtn);

        KeyboardRow keyboard2Row = new KeyboardRow();
        // Append keyboard to second row
        KeyboardButton helpBtn = new KeyboardButton();
        helpBtn.setText("/help");
        keyboard2Row.add(helpBtn);

        // Append all rows to keyboard
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboard2Row);
        // append list to keyboard
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }
}
