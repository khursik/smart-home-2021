package ru.sbt.mipt.oop.handler.controller.impl;

import ru.sbt.mipt.oop.handler.controller.Controller;
import ru.sbt.mipt.oop.handler.model.Model;
import ru.sbt.mipt.oop.models.events.SensorEvent;

public class ControllerImpl implements Controller {
    private final Model model;

    public ControllerImpl(Model model) {
        this.model = model;
    }

    @Override
    public void gotEvent(SensorEvent event) {
        model.handleEvent(event);
    }

    @Override
    public void refresh() {
        model.loadData();
    }

}
