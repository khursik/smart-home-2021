package ru.sbt.mipt.oop.handler.controller.impl;

import ru.sbt.mipt.oop.handler.controller.Controller;
import ru.sbt.mipt.oop.handler.model.Manager;
import ru.sbt.mipt.oop.models.events.SensorEvent;

public class ControllerImpl implements Controller {
    private final Manager manager;

    public ControllerImpl(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void gotEvent(SensorEvent event) {
        manager.handleEvent(event);
    }

    @Override
    public void refresh() {
        manager.loadData();
    }

}
