package ru.courses2.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountTests {
    @Test
    @DisplayName("Геттер и сеттер на имя клиента")
    public void nameClientCorrect() {
        String name = "Петр Петрович";
        Account account = new Account("Иван Иванович");
        account.setNameClient(name);
        Assertions.assertTrue(name.equals(account.getNameClient()));
    }

    @Test
    @DisplayName("Null или пустое имя клиента")
    public void nameClientNullOrEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Account(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Account(""));
    }

    @Test
    @DisplayName("Валюта-значение")
    public void CurrencyValue() {
        Account account = new Account("Сан Саныч");
        account.setCurrencyValue(Currency.RUB, Integer.valueOf(10));
        System.out.println(account.getCurrencyValue());
        account.setCurrencyValue(Currency.RUB, Integer.valueOf(23));
        System.out.println(account.getCurrencyValue());
        Assertions.assertThrows(IllegalArgumentException.class, () -> account.setCurrencyValue(Currency.RUB, Integer.valueOf(-1)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> account.setCurrencyValue(Currency.EUR, Integer.valueOf(-2)));
        System.out.println(account);
    }

    @Test
    @DisplayName("Сохранение/Восстановление данных счета")
    public void accountSaveRestore() {
        Account account = new Account("Сан Саныч");
        account.setCurrencyValue(Currency.RUB, Integer.valueOf(333));
        account.setCurrencyValue(Currency.AED, Integer.valueOf(555));
        System.out.println("Данные на момент первого сохранения: " + account);
        Account save1 = account.saveUser();
        account.setCurrencyValue(Currency.RUB, Integer.valueOf(111));
        account.setCurrencyValue(Currency.JOY, Integer.valueOf(44));
        System.out.println("Данные на момент второго сохранения: " + account);
        Account save2 = account.saveUser();
        account.setCurrencyValue(Currency.TRY, Integer.valueOf(666));
        System.out.println("Окончательные данные перед восстановлениями: " + account);
        account.restoreUser();
        System.out.println("Данные второго сохранения: " + account);
        Assertions.assertTrue(account.equals(save2)); // ожидаем, что данные совпадают, тк восстановились после изменений
        account.restoreUser();
        System.out.println("Данные первого сохранения: " + account);
        Assertions.assertTrue(account.equals(save1)); // ожидаем, что данные совпадают
        account.setNameClient("qwerty");
        account.setCurrencyValue(Currency.USD, Integer.valueOf(888));
        System.out.println("Добавили еще данные: " + account);
        Assertions.assertFalse(account.equals(save1)); // ожидаем, что данные НЕ совпадают, тк изменились после восстановления
    }

    @Test
    @DisplayName("Отмена изменений")
    public void accountUndo() {
        Account account = new Account("Петр Петрович");
        account.setCurrencyValue(Currency.USD, Integer.valueOf(12));
        Account save1 = account.clon();
        account.setCurrencyValue(Currency.RUB, Integer.valueOf(333));
        Account save2 = account.clon();
        account.setCurrencyValue(Currency.RUB, Integer.valueOf(555));
        System.out.println(account);
        account.undo();
        System.out.println(account);
        Assertions.assertTrue(account.equals(save2)); // ожидаем, что данные совпадают, тк вернули прежние значения
        account.undo();
        System.out.println(account);
        Assertions.assertTrue(account.equals(save1)); // ожидаем, что данные совпадают, тк вернули прежние значения
        account.setNameClient("Василий Алибабаевич");
        Assertions.assertFalse(account.equals(save1)); // ожидаем, что данные НЕ совпадают, тк изменились после восстановления
    }
}
