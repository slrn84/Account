package ru.courses2.bank;

import java.util.Map;

public class Save {
    private final String nameClient;
    private final Map<Currency,Integer> currencyValue;
    public Save(Account account) {
        this.nameClient = account.getNameClient();
        this.currencyValue = account.getCurrencyValue();
    }
    public String getNameClient() {
        return nameClient;
    }
    public Map<Currency,Integer> getCurrencyValue() {
        return currencyValue;
    }
}