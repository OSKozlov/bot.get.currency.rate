package com.telegram.ccyrate.bot.service;

import com.telegram.ccyrate.bot.ApplicationContextProvider;
import com.telegram.ccyrate.bot.config.BotConfig;
import com.telegram.ccyrate.bot.model.UahToForeignCcyModel;
import com.telegram.ccyrate.bot.util.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CcyRateService implements RateService {

    private final Logger logger = LoggerFactory.getLogger(CcyRateService.class);

    private BotConfig botConfig;

    public CcyRateService() {
        botConfig = ApplicationContextProvider.getApplicationContext().getBean(BotConfig.class);
    }

    @Override
    public Map<String, UahToForeignCcyModel> getUahToForeignCcyRates() {
        try {
            List<UahToForeignCcyModel> uahToForeignCcyModelList = new ArrayList<>();
            JSONArray jsonArray = JsonReader.readJsonFromUrl(botConfig.getUahToForeignCcyRateRef());
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                UahToForeignCcyModel model = new UahToForeignCcyModel();
                model.setStartDate(item.getString("StartDate"));
                model.setTimeSign(item.getString("TimeSign"));
                model.setCurrencyCode(item.getString("CurrencyCode"));
                model.setCurrencyCodeL(item.getString("CurrencyCodeL"));
                model.setUnits(item.getInt("Units"));
                model.setAmount(item.getBigDecimal("Amount"));
                uahToForeignCcyModelList.add(model);
            }
            return uahToForeignCcyModelList.stream()
                    .collect(Collectors.toMap(UahToForeignCcyModel::getCurrencyCodeL, Function.identity()));
        } catch (IOException e) {
            logger.error("Error has occured while retrieving currency rates...");
            return Collections.emptyMap();
        }
    }

    @Override
    public UahToForeignCcyModel getUahToForeignCccyRate(String foreignCcyCodeL) {
        return getUahToForeignCcyRates().get(foreignCcyCodeL);
    }
}
