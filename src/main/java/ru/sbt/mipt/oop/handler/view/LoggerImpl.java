package ru.sbt.mipt.oop.handler.view;

public class LoggerImpl implements Logger {
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
