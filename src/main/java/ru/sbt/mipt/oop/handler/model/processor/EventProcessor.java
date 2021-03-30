package ru.sbt.mipt.oop.handler.model.processor;

import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.models.SmartHome;
import ru.sbt.mipt.oop.models.events.SensorEvent;

public interface EventProcessor {
    String process(SensorEvent event, SmartHome home, CommandSender commandSender);
}
