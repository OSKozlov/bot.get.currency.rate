package com.telegram.ccyrate.bot.model;

import java.math.BigDecimal;

/**
 * This class represents model that contains up-to-date data on hryvnia exchange rates up to
 * foreign currency
 *
 * For instance: "StartDate":"08.01.2024","TimeSign":"0000","CurrencyCode":"036",
 *               "CurrencyCodeL":"AUD","Units":1,"Amount":25.4586
 */
public class UahToForeignCcyModel {

    private String startDate;
    private String timeSign;
    private String currencyCode;
    private String currencyCodeL;
    private Integer units;
    private BigDecimal amount;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTimeSign() {
        return timeSign;
    }

    public void setTimeSign(String timeSign) {
        this.timeSign = timeSign;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCodeL() {
        return currencyCodeL;
    }

    public void setCurrencyCodeL(String currencyCodeL) {
        this.currencyCodeL = currencyCodeL;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
