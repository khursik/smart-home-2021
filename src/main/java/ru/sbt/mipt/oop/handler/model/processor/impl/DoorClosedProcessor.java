package ru.sbt.mipt.oop.handler.model.processor.impl;

import ru.sbt.mipt.oop.handler.model.commands.SensorCommand;
import ru.sbt.mipt.oop.handler.model.commands.impl.LightOffCommand;
import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.models.Room;
import ru.sbt.mipt.oop.models.SmartHome;
import ru.sbt.mipt.oop.models.events.impl.DoorClosedEvent;
import ru.sbt.mipt.oop.models.things.Thing;
import ru.sbt.mipt.oop.models.things.impl.Door;
import ru.sbt.mipt.oop.models.things.impl.Light;

public class DoorClosedProcessor implements EventProcessor<DoorClosedEvent> {

    @Override
    public String process(DoorClosedEvent event, SmartHome home, CommandSender commandSender) {
        for (Room room : home.getRooms()) {
            for (Thing door : room.getSpecified(Door.class)) {
                if(door.getId().equals(event.getObjectId())){
                    //Предполагается, что id уникальный
                    ((Door)door).setOpen(true);
                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                    if (room.getName().equals("hall")) {
                        for (Room homeRoom : home.getRooms()) {
                            for (Thing light : homeRoom.getSpecified(Light.class)) {
                                ((Light)light).setOn(false);
                                SensorCommand command = new LightOffCommand(light.getId());
                                commandSender.sendCommand(command);
                            }
                        }
                    }
                    return "Door " + door.getId() + " in room " + room.getName() + " was closed.";
                }
            }
        }
        return null;
    }
}
