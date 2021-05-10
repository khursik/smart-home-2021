package ru.sbt.mipt.oop.handler.model.impl;

import ru.sbt.mipt.oop.handler.model.Manager;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.SendSMSEvent;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmEvent;

public abstract class State {
    private final Manager manager;
    protected EventProcessor alarmSystemEventHandler;

    protected State(AlarmSystemManager alarmSystemManager) {
        this.manager = alarmSystemManager.manager;
    }

    void template(SensorEvent event) {
        if (event instanceof AlarmEvent) {
            alarmSystemEventHandler.process(event, null, null, null);
            return;
        }

        process(event);

        if (needToSendSms()) {
            manager.handleEvent(new SendSMSEvent(event.getObjectId()));
        }
    }

    void process(SensorEvent event) {
        manager.handleEvent(event);
    }

    boolean needToSendSms() {
        return true;
    }

}
