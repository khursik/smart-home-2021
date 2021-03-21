package ru.sbt.mipt.oop.handler.model.processor.impl;

import ru.sbt.mipt.oop.handler.model.commands.SensorCommand;
import ru.sbt.mipt.oop.handler.model.commands.impl.LightOffCommand;
import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.handler.view.Logger;
import ru.sbt.mipt.oop.models.Room;
import ru.sbt.mipt.oop.models.SmartHome;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.DoorClosedEvent;
import ru.sbt.mipt.oop.models.events.impl.LightOffEvent;
import ru.sbt.mipt.oop.models.things.Thing;
import ru.sbt.mipt.oop.models.things.impl.Door;
import ru.sbt.mipt.oop.models.things.impl.Light;

public class DoorClosedProcessor implements EventProcessor {

    @Override
    public void process(SensorEvent event, SmartHome home, CommandSender commandSender, Logger logger) {
        if (event instanceof DoorClosedEvent) {
            home.execute((thing, name) -> {
                if (thing.getId().equals(event.getObjectId()) && (thing instanceof Door)) {
                    ((Door) thing).setOpen(false);
                    logger.printMessage("Door " + event.getObjectId() +" in room " + name + " was closed.");
                }
            });
        }
    }
}
