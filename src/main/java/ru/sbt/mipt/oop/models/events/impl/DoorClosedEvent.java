package ru.sbt.mipt.oop.models.events.impl;

import ru.sbt.mipt.oop.models.events.SensorEvent;

public class DoorClosedEvent extends SensorEvent {
    public DoorClosedEvent(String objectId) {
        super(objectId);
    }
}
