package ru.sbt.mipt.oop.models.events.impl;

import ru.sbt.mipt.oop.models.events.SensorEvent;

public class SendSMSEvent extends SensorEvent {
    public SendSMSEvent(String objectId) {
        super(objectId);
    }
}
