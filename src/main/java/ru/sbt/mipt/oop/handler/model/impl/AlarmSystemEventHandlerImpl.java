package ru.sbt.mipt.oop.handler.model.impl;

import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmDeactivate;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmEvent;

public class AlarmSystemEventHandlerImpl implements AlarmSystemEventHandler{
    private final AlarmSystemManager alarmSystemManager;

    public AlarmSystemEventHandlerImpl(AlarmSystemManager alarmSystemManager) {
        this.alarmSystemManager = alarmSystemManager;
    }

    @Override
    public void processAlarmEvent(AlarmEvent alarmEvent) {
        if (alarmEvent instanceof AlarmDeactivate) {
            if (alarmSystemManager.code.equals(((AlarmDeactivate) alarmEvent).getCode())) {
                alarmSystemManager.changeState(new DeactivatedState(alarmSystemManager));
                alarmSystemManager.code = null;
            }
        }
    }
}
