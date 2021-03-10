package ru.sbt.mipt.oop.handler.model.commands.sender.impl;

import ru.sbt.mipt.oop.handler.model.commands.SensorCommand;
import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;

public class ConsoleCommandSender implements CommandSender {
    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
