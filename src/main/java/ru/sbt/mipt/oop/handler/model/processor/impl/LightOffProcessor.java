package ru.sbt.mipt.oop.handler.model.processor.impl;

import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.handler.view.Logger;
import ru.sbt.mipt.oop.models.homeitems.SmartHome;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.LightOffEvent;
import ru.sbt.mipt.oop.models.homeitems.Light;

public class LightOffProcessor implements EventProcessor {

    @Override
    public void process(SensorEvent event, SmartHome home, CommandSender commandSender, Logger logger) {
        if (event instanceof LightOffEvent) {
            home.execute((homeItem, name) -> {
                if (homeItem.getId().equals(event.getObjectId()) && (homeItem instanceof Light)) {
                    ((Light) homeItem).setOn(false);
                    logger.printMessage("Light " + event.getObjectId() +" in room " + name + " was turned off.");
                }
            });
        }
    }
}