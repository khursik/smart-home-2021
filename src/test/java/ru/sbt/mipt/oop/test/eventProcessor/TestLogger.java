package ru.sbt.mipt.oop.test.eventProcessor;

import ru.sbt.mipt.oop.handler.view.Logger;

public class TestLogger implements Logger {
    String cashedMessage;
    @Override
    public void printMessage(String message) {
        cashedMessage = message;
    }
}
