package ru.sbt.mipt.oop.handler.view;

public class ConsoleView implements View {
    @Override
    public void addMessage(String message) {
        System.out.println(message);
    }
}
