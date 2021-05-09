package ru.sbt.mipt.oop.test.eventProcessor;

import ru.sbt.mipt.oop.handler.model.commands.SensorCommand;
import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;

public class TestCommandSender implements CommandSender {
    SensorCommand command;
    int counter = 0;
    @Override
    public void sendCommand(SensorCommand command) {
        this.command = command;
        counter++;
    }
}
