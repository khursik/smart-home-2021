package ru.sbt.mipt.oop.handler.model.impl;

import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmActivate;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmEvent;

public class DeactivatedAlarmSystemEventHandlerImpl implements AlarmSystemEventHandler{
    private final AlarmSystemManager alarmSystemManager;

    public DeactivatedAlarmSystemEventHandlerImpl(AlarmSystemManager alarmSystemManager) {
        this.alarmSystemManager = alarmSystemManager;
    }

    @Override
    public void processAlarmEvent(AlarmEvent alarmEvent) {
        if (alarmEvent instanceof AlarmActivate) {
            alarmSystemManager.code = alarmEvent.getCode();
            alarmSystemManager.changeState(new ActivatedState(alarmSystemManager));
        }
    }
}
