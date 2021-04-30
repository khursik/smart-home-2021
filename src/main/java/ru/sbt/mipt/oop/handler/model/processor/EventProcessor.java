package ru.sbt.mipt.oop.handler.model.processor;

import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.view.Logger;
import ru.sbt.mipt.oop.models.homeitems.SmartHome;
import ru.sbt.mipt.oop.models.events.SensorEvent;

public interface EventProcessor {
    void process(SensorEvent event, SmartHome home, CommandSender commandSender, Logger logger);
}
