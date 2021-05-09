package ru.sbt.mipt.oop.handler.model.impl;

import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmActivate;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmDeactivate;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmEvent;


public class ActivatedAlarmSystemEventHandlerImpl implements AlarmSystemEventHandler {
    private final AlarmSystemManager alarmSystemManager;

    public ActivatedAlarmSystemEventHandlerImpl(AlarmSystemManager alarmSystemManager) {
        this.alarmSystemManager = alarmSystemManager;
    }


    @Override
    public void processAlarmEvent(AlarmEvent alarmEvent) {
        if (alarmEvent instanceof AlarmDeactivate) {
            if (alarmSystemManager.code.equals(alarmEvent.getCode())) {
                alarmSystemManager.changeState(new DeactivatedState(alarmSystemManager));
                alarmSystemManager.code = null;
            } else {
                alarmSystemManager.changeState(new AlarmState(alarmSystemManager));
            }
        }
    }
}
