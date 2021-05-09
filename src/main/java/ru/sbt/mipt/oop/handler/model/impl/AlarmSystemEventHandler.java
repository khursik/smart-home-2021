package ru.sbt.mipt.oop.handler.model.impl;

import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmEvent;

public interface AlarmSystemEventHandler {
    void processAlarmEvent(AlarmEvent alarmEvent);
}
