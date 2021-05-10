package ru.sbt.mipt.oop.handler.model.commands.sender;

import ru.sbt.mipt.oop.handler.model.commands.SensorCommand;

public interface CommandSender {
    void sendCommand(SensorCommand command);
}
