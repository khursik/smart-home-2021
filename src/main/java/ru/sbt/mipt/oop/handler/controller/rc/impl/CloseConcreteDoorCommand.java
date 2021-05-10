package ru.sbt.mipt.oop.handler.controller.rc.impl;

import ru.sbt.mipt.oop.handler.controller.rc.Command;
import ru.sbt.mipt.oop.handler.model.Manager;
import ru.sbt.mipt.oop.models.events.impl.DoorClosedEvent;


public class CloseConcreteDoorCommand implements Command {
    private final String doorId;
    private final Manager manager;

    public CloseConcreteDoorCommand(String doorId, Manager manager) {
        this.doorId = doorId;
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.handleEvent(new DoorClosedEvent(doorId));
    }
}
