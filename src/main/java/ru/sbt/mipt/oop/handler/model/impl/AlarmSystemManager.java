package ru.sbt.mipt.oop.handler.model.impl;

import ru.sbt.mipt.oop.handler.model.Manager;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.SendSMSEvent;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmActivate;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmDeactivate;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmEvent;

public class AlarmSystemManager implements Manager {
    final Manager manager;
    private State state;
    String code;

    public AlarmSystemManager(Manager manager) {
        this.manager = manager;
        state = new DeactivatedState(this);
    }

    void changeState(State state) {
        this.state = state;
    }

    @Override
    public void loadData() {
        manager.loadData();
    }

    @Override
    public void handleEvent(SensorEvent event) {
        state.template(event);
    }
}

abstract class State {
    private final Manager manager;
    protected AlarmSystemEventHandler alarmSystemEventHandler;

    protected State(AlarmSystemManager alarmSystemManager) {
        this.manager = alarmSystemManager.manager;
    }

    void template(SensorEvent event) {
        if (event instanceof AlarmEvent) {
            alarmSystemEventHandler.processAlarmEvent((AlarmEvent) event);
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
class DeactivatedState extends State {
    public DeactivatedState(AlarmSystemManager manager) {
        super(manager);
        this.alarmSystemEventHandler = new DeactivatedAlarmSystemEventHandlerImpl(manager);
    }

    @Override
    boolean needToSendSms() {
        return false;
    }
}
class ActivatedState extends State {
    public ActivatedState(AlarmSystemManager manager) {
        super(manager);
        this.alarmSystemEventHandler = new ActivatedAlarmSystemEventHandlerImpl(manager);
    }
}
class AlarmState extends State {
    public AlarmState(AlarmSystemManager manager) {
        super(manager);
        this.alarmSystemEventHandler = new DeactivatedAlarmSystemEventHandlerImpl(manager);
    }

    @Override
    void process(SensorEvent event) {
    }
}
