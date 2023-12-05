package ru.courses2.bank;

public abstract class Command {

    public abstract void execute();

    public abstract void undo();
}