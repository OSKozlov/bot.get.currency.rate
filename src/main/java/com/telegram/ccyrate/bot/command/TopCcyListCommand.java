package com.telegram.ccyrate.bot.command;

import com.telegram.ccyrate.bot.model.UahToForeignCcyModel;
import com.telegram.ccyrate.bot.service.RateService;
import com.telegram.ccyrate.bot.service.SendBotMessageService;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.telegram.ccyrate.bot.command.CommandName.SHOW;

public class TopCcyListCommand implements Command {

    private static final List TOP_CURRENCIES = Arrays.asList("USD","EUR","GBP","PLN","CHF");

    private static final String MESSAGE_CURRENCY_EXCHANGE = "Please choose currency pair: ";

    private final SendBotMessageService sendBotMessageService;

    private final RateService rateService;

    public TopCcyListCommand(SendBotMessageService sendBotMessageService, RateService rateService) {
        this.sendBotMessageService = sendBotMessageService;
        this.rateService = rateService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendInlineKeyBoardMessage(update.getMessage().getChatId().toString(), MESSAGE_CURRENCY_EXCHANGE,
                createInlineKeyBoard());
    }

    private InlineKeyboardMarkup createInlineKeyBoard() {
        List<InlineKeyboardButton> keyboardButtonsList = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        Map<String, UahToForeignCcyModel> map = rateService.getUahToForeignCcyRates();
        for(Map.Entry entry : map.entrySet()) {
            String ccy = (String) entry.getKey();
            if (TOP_CURRENCIES.contains(ccy)) {
                String ccypair = "UAH" + ccy;
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText(ccypair);
                inlineKeyboardButton.setCallbackData(SHOW.getCommandName() + StringUtils.SPACE + (String) entry.getKey());

                keyboardButtonsList.add(inlineKeyboardButton);
            }
        }
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        for(InlineKeyboardButton button : keyboardButtonsList) {
            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
            keyboardButtonsRow.add(button);
            rowList.add(keyboardButtonsRow);
        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
