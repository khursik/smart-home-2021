package ru.sbt.mipt.oop.handler.model.impl;

import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.handler.view.Logger;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmActivate;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmEvent;
import ru.sbt.mipt.oop.models.homeitems.SmartHome;

public class DeactivatedAlarmSystemEventHandlerImpl implements EventProcessor {
    private final AlarmSystemManager alarmSystemManager;

    public DeactivatedAlarmSystemEventHandlerImpl(AlarmSystemManager alarmSystemManager) {
        this.alarmSystemManager = alarmSystemManager;
    }


    @Override
    public void process(SensorEvent event, SmartHome home, CommandSender commandSender, Logger logger) {
        if (event instanceof AlarmActivate) {
            alarmSystemManager.activate(((AlarmEvent) event).getCode());
        }
    }
}
