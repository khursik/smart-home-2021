package ru.sbt.mipt.oop.handler.model.processor.impl;

import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.models.Room;
import ru.sbt.mipt.oop.models.SmartHome;
import ru.sbt.mipt.oop.models.events.impl.LightOffEvent;
import ru.sbt.mipt.oop.models.things.Thing;
import ru.sbt.mipt.oop.models.things.impl.Light;

public class LightOffProcessor implements EventProcessor<LightOffEvent> {

    @Override
    public String process(LightOffEvent event, SmartHome home, CommandSender commandSender) {
        for (Room room : home.getRooms()) {
            for (Thing light : room.getSpecified(Light.class)) {
                if(light.getId().equals(event.getObjectId())){
                    //Предполагается, что id уникальный
                    ((Light)light).setOn(false);
                    return "Light " + light.getId() + " in room " + room.getName() + " was turned off.";
                }
            }
        }
        return null;
    }
}