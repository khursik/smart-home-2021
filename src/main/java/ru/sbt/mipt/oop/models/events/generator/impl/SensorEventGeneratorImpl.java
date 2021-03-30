package ru.sbt.mipt.oop.models.events.generator.impl;

import ru.sbt.mipt.oop.handler.controller.Controller;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.generator.SensorEventGenerator;
import ru.sbt.mipt.oop.models.events.impl.DoorClosedEvent;
import ru.sbt.mipt.oop.models.events.impl.DoorOpenEvent;
import ru.sbt.mipt.oop.models.events.impl.LightOffEvent;
import ru.sbt.mipt.oop.models.events.impl.LightOnEvent;

public class SensorEventGeneratorImpl implements SensorEventGenerator {


    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        String objectId = "" + ((int) (10 * Math.random()));
        switch ((int) (4 * Math.random())) {
            case 0:
                return new LightOnEvent(objectId);
            case 1:
                return new LightOffEvent(objectId);
            case 2:
                return new DoorClosedEvent(objectId);
            case 3:
                return new DoorOpenEvent(objectId);
        }
        return null;
    }

    @Override
    public void start(Controller controller) {
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            controller.gotEvent(event);
            event = getNextSensorEvent();
        }
    }
}
