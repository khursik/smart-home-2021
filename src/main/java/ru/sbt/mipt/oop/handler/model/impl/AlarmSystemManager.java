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

    public AlarmSystemManager(Manager manager) {
        this.manager = manager;
        state = new DeactivatedState(this);
    }
    void activate(String code) {
        changeState(new ActivatedState(this, code));
    }

    void deactivate() {
        changeState(new DeactivatedState(this));
    }

    void alarm(String code) {
        changeState(new AlarmState(this, code));
    }

    private void changeState(State state) {
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

