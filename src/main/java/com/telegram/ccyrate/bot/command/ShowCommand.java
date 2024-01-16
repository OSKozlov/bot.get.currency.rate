package com.telegram.ccyrate.bot.command;

import com.telegram.ccyrate.bot.model.UahToForeignCcyModel;
import com.telegram.ccyrate.bot.service.RateService;
import com.telegram.ccyrate.bot.service.SendBotMessageService;
import com.telegram.ccyrate.bot.state.BotState;
import com.telegram.ccyrate.bot.state.BotStateMgr;
import com.telegram.ccyrate.bot.state.StartState;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.util.Map;

public class ShowCommand implements Command {

    private static final String MESSAGE_CURRENCY_EXCHANGE = "Rate = ";

    private final SendBotMessageService sendBotMessageService;

    private final RateService rateService;

    public ShowCommand(SendBotMessageService sendBotMessageService, RateService rateService) {
           this.sendBotMessageService = sendBotMessageService;
           this.rateService = rateService;
    }

    @Override
    public void execute(Update update) {
        BotState state = BotStateMgr.getInstance().getState();
        if (!(state instanceof StartState)) {
            return;
        }

        String messageText = update.getMessage().getText().trim();
        String ccy = messageText.split(" ")[1].toUpperCase();
        Map<String, UahToForeignCcyModel> map = rateService.getUahToForeignCcyRates();
        UahToForeignCcyModel model = map.get(ccy);
        BigDecimal rate = model.getAmount();
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),
                MESSAGE_CURRENCY_EXCHANGE + rate.toString());
    }
}
