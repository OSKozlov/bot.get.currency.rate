package com.telegram.ccyrate.bot.service;

import com.telegram.ccyrate.bot.model.UahToForeignCcyModel;

import java.util.Map;

public interface RateService {

    Map<String, UahToForeignCcyModel> getUahToForeignCcyRates();

    UahToForeignCcyModel getUahToForeignCccyRate(String foreignCcyCodeL);

    String getServiceName();

}
