package com.telegram.ccyrate.bot.command;

import com.telegram.ccyrate.bot.model.UahToForeignCcyModel;
import com.telegram.ccyrate.bot.provider.ServiceLocator;
import com.telegram.ccyrate.bot.service.CcyRateService;
import com.telegram.ccyrate.bot.service.RateService;
import com.telegram.ccyrate.bot.service.SendBotMessageService;
import com.telegram.ccyrate.bot.state.BotStateMgr;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Implementation of Show command {@link Command}
 */
public class ShowCommand implements Command {

    private static final String MESSAGE_CURRENCY_EXCHANGE     = "Rate for UAH/";
    private static final String BOT_IS_NOT_AVAILABLE_MESSAGE  = "Please start bot first via invocation command (/start).";
    private static final String CURRENCY_IS_NOT_FOUND_MESSAGE = "Currency is not found. Please provide another one.";

    private final SendBotMessageService sendBotMessageService;

    private final RateService rateService;

    public ShowCommand(SendBotMessageService sendBotMessageService) {
           this.sendBotMessageService = sendBotMessageService;
           this.rateService = ServiceLocator.getService(CcyRateService.CCY_RATE_SVC);
    }

    @Override
    public void execute(Update update) {
        String chatId = update.hasMessage() ? update.getMessage().getChatId().toString() :
                update.getCallbackQuery().getMessage().getChatId().toString();
        if (!BotStateMgr.getInstance().isBotStarted()) {
            sendBotMessageService.sendMessage(chatId, BOT_IS_NOT_AVAILABLE_MESSAGE);
            return;
        }

        String ccy = retrieveDataFromMessage(update.hasMessage() ? update.getMessage().getText() : update.getCallbackQuery().getData());
        Map<String, UahToForeignCcyModel> map = rateService.getUahToForeignCcyRates();
        UahToForeignCcyModel model = map.get(ccy);
        if (model == null) {
            sendBotMessageService.sendMessage(chatId, CURRENCY_IS_NOT_FOUND_MESSAGE);
            return;
        }
        BigDecimal rate = model.getAmount();
        sendBotMessageService.sendMessage(chatId,MESSAGE_CURRENCY_EXCHANGE + ccy + ":" + StringUtils.SPACE
                + rate.toString());
    }

    private String retrieveDataFromMessage(String message) {
        String messageText = message.trim();
        return messageText.split(" ")[1].toUpperCase();
    }
}
