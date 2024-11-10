package com.telegram.ccyrate.bot.provider;

import com.telegram.ccyrate.bot.service.CcyRateService;

public class InitialContext {
    public Object lookup(String serviceName) {
        if (CcyRateService.CCY_RATE_SVC.equalsIgnoreCase(serviceName)) {
            return new CcyRateService();
        }
        return null;
    }
}
