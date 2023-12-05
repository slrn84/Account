package ru.courses2.bank;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public abstract class AccountBank {
    protected String nameClient;
    protected Map<Currency,Integer> currencyValue = new HashMap<>();;

    public AccountBank(String nameClient) {
        setNameClient(nameClient);
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public Map getCurrencyValue() {
        return new HashMap(currencyValue);
    }

    public void setCurrencyValue(Currency currency, Integer value) {
        currencyValue.put(currency, value);
    }
    void setCurrencyValue(Map<Currency,Integer> cv) {
        currencyValue = new HashMap<>(cv);
    }

    @Override
    public String toString() {
        return "AccountBank{\'" + nameClient + "': " + currencyValue.toString() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountBank that)) return false;
        return Objects.equals(getNameClient(), that.getNameClient()) && Objects.equals(getCurrencyValue(), that.getCurrencyValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameClient(), getCurrencyValue());
    }


}
