package ru.sbt.mipt.oop.models.events.generator.impl;

import ru.sbt.mipt.oop.handler.controller.Controller;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.generator.SensorEventGenerator;
import ru.sbt.mipt.oop.models.events.impl.*;
import ru.sbt.mipt.oop.side.library.events.SensorEventsManager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class SensorEventManagerAdapter implements SensorEventGenerator {
    private final Map<String, Function<String, SensorEvent>> ccSensorEventTypeToConstructor;

    public SensorEventManagerAdapter() {
        ccSensorEventTypeToConstructor = new HashMap<>();
        ccSensorEventTypeToConstructor.put("LightIsOn", LightOnEvent::new);
        ccSensorEventTypeToConstructor.put("LightIsOff", LightOffEvent::new);
        ccSensorEventTypeToConstructor.put("DoorIsOpen", DoorOpenEvent::new);
        ccSensorEventTypeToConstructor.put("DoorIsClosed", DoorClosedEvent::new);
        ccSensorEventTypeToConstructor.put("DoorIsLocked", DoorLockedEvent::new);
        ccSensorEventTypeToConstructor.put("DoorIsUnlocked", DoorUnlockedEvent::new);
    }


    @Override
    public void start(Controller controller) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(event -> {
            Function<String, SensorEvent> eventConstructor = ccSensorEventTypeToConstructor.get(event.getEventType());
            controller.gotEvent(eventConstructor.apply(event.getObjectId()));
        });
        sensorEventsManager.start();
    }
}
