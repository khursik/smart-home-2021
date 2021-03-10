package ru.sbt.mipt.oop.handler.model.processor.impl;

import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.models.Room;
import ru.sbt.mipt.oop.models.SmartHome;
import ru.sbt.mipt.oop.models.events.impl.LightOnEvent;
import ru.sbt.mipt.oop.models.things.Thing;
import ru.sbt.mipt.oop.models.things.impl.Light;

public class LightOnProcessor implements EventProcessor<LightOnEvent> {

    @Override
    public String process(LightOnEvent event, SmartHome home, CommandSender commandSender) {
        for (Room room : home.getRooms()) {
            for (Thing light : room.getSpecified(Light.class)) {
                if(light.getId().equals(event.getObjectId())){
                    //Предполагается, что id уникальный
                    ((Light)light).setOn(true);
                    return "Light " + light.getId() + " in room " + room.getName() + " was turned on.";
                }
            }
        }
        return null;
    }
}
