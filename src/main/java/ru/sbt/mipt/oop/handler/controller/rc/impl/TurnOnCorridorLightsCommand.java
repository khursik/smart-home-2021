package ru.sbt.mipt.oop.handler.controller.rc.impl;

import ru.sbt.mipt.oop.handler.controller.rc.Command;
import ru.sbt.mipt.oop.handler.model.Manager;
import ru.sbt.mipt.oop.models.events.impl.LightOnEvent;

import java.util.List;

public class TurnOnCorridorLightsCommand implements Command {
    private final List<String> lights;
    private final Manager manager;

    public TurnOnCorridorLightsCommand(List<String> lights, Manager manager) {
        this.lights = lights;
        this.manager = manager;
    }

    @Override
    public void execute() {
        for (String light : lights) {
            manager.handleEvent(new LightOnEvent(light));
        }
    }
}
