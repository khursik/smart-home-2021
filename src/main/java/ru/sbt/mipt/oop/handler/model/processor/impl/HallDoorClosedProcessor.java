package ru.sbt.mipt.oop.handler.model.processor.impl;

import ru.sbt.mipt.oop.handler.model.commands.SensorCommand;
import ru.sbt.mipt.oop.handler.model.commands.impl.LightOffCommand;
import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.handler.view.Logger;
import ru.sbt.mipt.oop.models.homeitems.SmartHome;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.DoorClosedEvent;
import ru.sbt.mipt.oop.models.homeitems.Door;
import ru.sbt.mipt.oop.models.homeitems.Light;

public class HallDoorClosedProcessor implements EventProcessor {

    @Override
    public void process(SensorEvent event, SmartHome home, CommandSender commandSender, Logger logger) {
        if (event instanceof DoorClosedEvent) {
            home.execute((homeItem, name) -> {
                if (homeItem.getId().equals(event.getObjectId()) && (homeItem instanceof Door) && (name.equals("hall"))) {
                    home.execute(((thing1, name1) -> {
                        if (thing1 instanceof Light) {
                            ((Light) thing1).setOn(false);
                            SensorCommand command = new LightOffCommand(thing1.getId());
                            commandSender.sendCommand(command);
                        }
                    }));
                }
            });
        }
    }
}
