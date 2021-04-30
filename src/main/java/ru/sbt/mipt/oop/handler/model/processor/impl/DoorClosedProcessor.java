package ru.sbt.mipt.oop.handler.model.processor.impl;

import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.handler.view.Logger;
import ru.sbt.mipt.oop.models.homeitems.SmartHome;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.DoorClosedEvent;
import ru.sbt.mipt.oop.models.homeitems.Door;

public class DoorClosedProcessor implements EventProcessor {

    @Override
    public void process(SensorEvent event, SmartHome home, CommandSender commandSender, Logger logger) {
        if (event instanceof DoorClosedEvent) {
            home.execute((homeItem, name) -> {
                if (homeItem.getId().equals(event.getObjectId()) && (homeItem instanceof Door)) {
                    ((Door) homeItem).setOpen(false);
                    logger.printMessage("Door " + event.getObjectId() +" in room " + name + " was closed.");
                }
            });
        }
    }
}
