package ru.sbt.mipt.oop.handler.controller.rc.impl;

import ru.sbt.mipt.oop.handler.controller.rc.Command;
import ru.sbt.mipt.oop.handler.model.impl.AlarmSystemManager;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmActivate;

public class ActivateAlarmCommand implements Command {
    private final AlarmSystemManager alarmSystemManager;
    private final String code;

    public ActivateAlarmCommand(AlarmSystemManager alarmSystemManager, String code) {
        this.alarmSystemManager = alarmSystemManager;
        this.code = code;
    }

    @Override
    public void execute() {
        alarmSystemManager.handleEvent(new AlarmActivate(code));
    }
}
