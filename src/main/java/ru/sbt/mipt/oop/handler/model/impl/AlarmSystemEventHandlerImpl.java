package ru.sbt.mipt.oop.handler.model.impl;

import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.handler.view.Logger;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmDeactivate;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmEvent;
import ru.sbt.mipt.oop.models.homeitems.SmartHome;

public class AlarmSystemEventHandlerImpl implements EventProcessor {
    private final AlarmSystemManager alarmSystemManager;
    private final String code;

    public AlarmSystemEventHandlerImpl(AlarmSystemManager alarmSystemManager, String code) {
        this.alarmSystemManager = alarmSystemManager;
        this.code = code;
    }


    @Override
    public void process(SensorEvent event, SmartHome home, CommandSender commandSender, Logger logger) {
        if (event instanceof AlarmDeactivate) {
            if (code.equals(((AlarmDeactivate) event).getCode())) {
                alarmSystemManager.deactivate();
            }
        }
    }
}
