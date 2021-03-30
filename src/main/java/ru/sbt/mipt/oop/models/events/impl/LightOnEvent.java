package ru.sbt.mipt.oop.models.events.impl;

import ru.sbt.mipt.oop.models.events.SensorEvent;

public class LightOnEvent extends SensorEvent {
    public LightOnEvent(String objectId) {
        super(objectId);
    }
}
