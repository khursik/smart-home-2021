package ru.sbt.mipt.oop.handler.controller.rc.impl;

import ru.sbt.mipt.oop.handler.controller.rc.Command;
import ru.sbt.mipt.oop.handler.model.impl.AlarmSystemManager;
import ru.sbt.mipt.oop.models.events.impl.alarm.TurnOnAlarm;

public class TurnOnAlarmCommand implements Command {

    private final AlarmSystemManager alarmSystemManager;

    public TurnOnAlarmCommand(AlarmSystemManager alarmSystemManager) {
        this.alarmSystemManager = alarmSystemManager;
    }

    @Override
    public void execute() {
        alarmSystemManager.handleEvent(new TurnOnAlarm());
    }
}
