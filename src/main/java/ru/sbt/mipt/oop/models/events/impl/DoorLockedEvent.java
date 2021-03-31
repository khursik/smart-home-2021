package ru.sbt.mipt.oop.models.events.impl;

import ru.sbt.mipt.oop.models.events.SensorEvent;

public class DoorLockedEvent extends SensorEvent {
    public DoorLockedEvent(String objectId) {
        super(objectId);
    }
}
