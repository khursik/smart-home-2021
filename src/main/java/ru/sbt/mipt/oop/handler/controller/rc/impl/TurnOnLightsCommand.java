package ru.sbt.mipt.oop.handler.controller.rc.impl;

import ru.sbt.mipt.oop.handler.controller.rc.Command;
import ru.sbt.mipt.oop.handler.model.Manager;
import ru.sbt.mipt.oop.models.events.impl.LightOffEvent;

public class TurnOnLightsCommand implements Command {
    private final Manager manager;

    public TurnOnLightsCommand(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.handleEvent(new LightOffEvent(null));
    }
}
