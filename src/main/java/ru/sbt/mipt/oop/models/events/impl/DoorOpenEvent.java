package ru.sbt.mipt.oop.models.events.impl;

import ru.sbt.mipt.oop.models.events.SensorEvent;

public class DoorOpenEvent extends SensorEvent {
    public DoorOpenEvent(String objectId) {
        super(objectId);
    }
}
