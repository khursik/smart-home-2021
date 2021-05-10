package ru.sbt.mipt.oop.models.events.generator.impl;

import ru.sbt.mipt.oop.handler.controller.Controller;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.generator.SensorEventGenerator;
import ru.sbt.mipt.oop.models.events.impl.*;
import ru.sbt.mipt.oop.side.library.events.SensorEventsManager;

public class SensorEventManagerAdapter implements SensorEventGenerator {


    @Override
    public void start(Controller controller) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(event -> {
            EventParsingType eventParsingType = EventParsingType.valueOf(event.getEventType());
            SensorEvent sensorEvent = eventParsingType.creator.create(event.getObjectId());
            controller.gotEvent(sensorEvent);
        });
        sensorEventsManager.start();
    }

    private enum EventParsingType {
        LightIsOn(LightOnEvent::new),
        LightIsOff(LightOffEvent::new),
        DoorIsOpen(DoorOpenEvent::new),
        DoorIsClosed(DoorClosedEvent::new),
        DoorIsLocked(DoorLockedEvent::new),
        DoorIsUnlocked(DoorUnlockedEvent::new);
        private final SensorEventCreator creator;

        EventParsingType(SensorEventCreator creator) {
            this.creator = creator;
        }
    }

    private interface SensorEventCreator<T extends SensorEvent> {
        T create(String id);
    }
}
