package ru.sbt.mipt.oop.handler.model.impl;

public class DeactivatedState extends State {
    public DeactivatedState(AlarmSystemManager manager) {
        super(manager);
        this.alarmSystemEventHandler = new DeactivatedAlarmSystemEventHandlerImpl(manager);
    }

    @Override
    boolean needToSendSms() {
        return false;
    }
}
