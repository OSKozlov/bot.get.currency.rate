package com.telegram.ccyrate.bot.provider;

import com.telegram.ccyrate.bot.service.RateService;

import java.util.HashMap;
import java.util.Map;

public class RateServiceCache {

    private final Map<String, RateService> services = new HashMap<>();

    public void addService(RateService rateService) {
        services.put(rateService.getServiceName(), rateService);
    }

    public RateService getService(String serviceName) {
        return services.get(serviceName);
    }
}
