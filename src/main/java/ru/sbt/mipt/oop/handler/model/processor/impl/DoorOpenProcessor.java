package ru.sbt.mipt.oop.handler.model.processor.impl;

import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.models.Room;
import ru.sbt.mipt.oop.models.SmartHome;
import ru.sbt.mipt.oop.models.events.impl.DoorOpenEvent;
import ru.sbt.mipt.oop.models.things.Thing;
import ru.sbt.mipt.oop.models.things.impl.Door;

public class DoorOpenProcessor implements EventProcessor<DoorOpenEvent> {

    @Override
    public String process(DoorOpenEvent event, SmartHome home, CommandSender commandSender) {
        for (Room room : home.getRooms()) {
            for (Thing door : room.getSpecified(Door.class)) {
                if(door.getId().equals(event.getObjectId())){
                    //Предполагается, что id уникальный
                    ((Door)door).setOpen(true);
                    return "Door " + door.getId() + " in room " + room.getName() + " was opened.";
                }
            }
        }
        return null;
    }
}
