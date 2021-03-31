package ru.sbt.mipt.oop.models.events.impl;

import ru.sbt.mipt.oop.models.events.SensorEvent;

public class DoorUnlockedEvent extends SensorEvent {
    public DoorUnlockedEvent(String objectId) {
        super(objectId);
    }
}
