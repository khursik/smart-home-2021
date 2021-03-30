package ru.sbt.mipt.oop.handler.model.processor.impl;

import ru.sbt.mipt.oop.handler.model.commands.SensorCommand;
import ru.sbt.mipt.oop.handler.model.commands.impl.LightOffCommand;
import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.models.Room;
import ru.sbt.mipt.oop.models.SmartHome;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.DoorClosedEvent;
import ru.sbt.mipt.oop.models.things.Thing;
import ru.sbt.mipt.oop.models.things.impl.Door;
import ru.sbt.mipt.oop.models.things.impl.Light;

public class DoorClosedProcessor implements EventProcessor {

    @Override
    public String process(SensorEvent event, SmartHome home, CommandSender commandSender) {
        if (event instanceof DoorClosedEvent) {
            for (Room room : home.getRooms()) {
                for (Thing door : room.getSpecified(Door.class)) {
                    if (door.getId().equals(event.getObjectId())) {
                        //Предполагается, что id уникальный
                        ((Door) door).setOpen(false);
                        return "Door " + door.getId() + " in room " + room.getName() + " was closed.";
                    }
                }
            }
        }
        return null;
    }
}
