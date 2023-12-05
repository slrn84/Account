package ru.courses2.bank;

import java.util.Stack;

public class Account extends AccountBank {
    public Account(String nameClient) {
        super(nameClient);
    }
    public Account clon(){
        Account copy = new Account(this.getNameClient());
        copy.setCurrencyValue(this.getCurrencyValue());
        return copy;
    }

    public void setNameClient(String nameClient) {
        if (nameClient == null || nameClient.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть null или пустым");
        }
        this.save();
        super.setNameClient(nameClient);
    }

    public void setCurrencyValue(Currency currency, Integer value) {

        if (value < 0) {
            throw new IllegalArgumentException("Количество валюты не может быть отрицательным");
        }
        this.save();
        super.setCurrencyValue(currency, value);
    }

    // -->> Задание №1 Часть 3: Для сохранения и восстановления будем использовать архитектурный шаблон Memento
    private Stack<Save> historyUser = new Stack<>();

    public Account saveUser() {
//        Account copy = new Account(this.getNameClient());
//        copy.setCurrencyValue(this.getCurrencyValue());
        historyUser.push(new Save(this));
//        return copy;
        return this.clon();
    }

    public void restoreUser() {
        if(historyUser.isEmpty()) return;
        Save save = historyUser.pop();
        this.nameClient = save.getNameClient();
        this.currencyValue = save.getCurrencyValue();
    } // <<-- Для сохранения и восстановления будем использовать архитектурный шаблон Memento

    // -->> Задание №1 Часть 2:Для отката последнего действия будем использовать архитектурный шаблон Memento
    private Stack<Save> historyFunction;
    private void save(){
        if (historyFunction == null) historyFunction = new Stack<>();
        historyFunction.push(new Save(this));
    }
    public void undo(){
        if(historyFunction.isEmpty()) return;
        Save save = historyFunction.pop();
        this.nameClient = save.getNameClient();
        this.currencyValue = save.getCurrencyValue();
    }
    // --<< Задание №1 Часть 2:Для отката последнего действия будем использовать архитектурный шаблон Memento

    // TODO: переделать на шаблон Command
    // -->> Задание №1 Часть 2:Для отката последнего действия будем использовать архитектурный шаблон Command
//    private final LinkedList<Command> undoStack = new LinkedList<>();
//    void execute(Command cmd) {
//        undoStack.push(cmd);
//        cmd.execute();
//    }
//    public void undo() {
//        if (undoStack.isEmpty())
//            return;
//        Command cmd = undoStack.pop();
//        cmd.undo();
//    }
//    final class SetNameClient extends Command {
//        private String name;
//        public SetNameClient(String name) {
//            this.name = name;
//        }
//
//        @Override
//        public void execute() {
//            setNameClient(this.name);
//        }
//
//        @Override
//        public void undo() {
//            setNameClient(this.name);
//        }
//    }
    // --<< Для отката последнего действия будем использовать архитектурный шаблон Command

}
