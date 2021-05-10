package ru.sbt.mipt.oop.handler.model.impl;

import ru.sbt.mipt.oop.models.events.SensorEvent;

public class AlarmState extends State {
    public AlarmState(AlarmSystemManager manager, String code) {
        super(manager);
        this.alarmSystemEventHandler = new AlarmSystemEventHandlerImpl(manager, code);
    }

    @Override
    void process(SensorEvent event) {
    }
}
