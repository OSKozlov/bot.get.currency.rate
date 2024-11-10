package com.telegram.ccyrate.bot.provider;

import com.telegram.ccyrate.bot.service.RateService;

public class ServiceLocator {

    private static RateServiceCache cache = new RateServiceCache();

    public static RateService getService(String serviceName) {

        RateService service = cache.getService(serviceName);

        if (service != null) {
            return service;
        }

        InitialContext context = new InitialContext();
        RateService newService = (RateService) context
                .lookup(serviceName);
        cache.addService(newService);
        return newService;
    }
}
