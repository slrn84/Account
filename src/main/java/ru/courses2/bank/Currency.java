package ru.courses2.bank;

import lombok.ToString;

@ToString
public enum Currency {
    RUB("Рубль"),
    USD("Доллар США"),
    EUR("Евро"),
    JOY("Йена"),
    TRY("Лира"),
    AED("Дирхам");
    String name;

    Currency(String string) {
        this.name = string;
    }

}

