package ru.sbt.mipt.oop.models.events.impl;

import ru.sbt.mipt.oop.models.events.SensorEvent;

public class LightOffEvent extends SensorEvent {
    public LightOffEvent(String objectId) {
        super(objectId);
    }
}
