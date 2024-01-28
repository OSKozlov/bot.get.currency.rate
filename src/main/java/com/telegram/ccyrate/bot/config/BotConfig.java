package com.telegram.ccyrate.bot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {

    @Value("${bot.name}")
    String botName;

    @Value("${bot.token}")
    String token;

    @Value("${bot.uahtoforeignccy.rate.ref}")
    String uahToForeignCcyRateRef;

    public String getBotName() {
        return botName;
    }

    public String getToken() {
        return token;
    }

    public String getUahToForeignCcyRateRef() {
        return uahToForeignCcyRateRef;
    }
}
