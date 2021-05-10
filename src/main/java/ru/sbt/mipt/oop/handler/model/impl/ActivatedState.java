package ru.sbt.mipt.oop.handler.model.impl;

public class ActivatedState extends State {
    public ActivatedState(AlarmSystemManager manager, String code) {
        super(manager);
        this.alarmSystemEventHandler = new ActivatedAlarmSystemEventHandlerImpl(manager, code);
    }
}
