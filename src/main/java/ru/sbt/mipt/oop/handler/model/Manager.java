package ru.sbt.mipt.oop.handler.model;

import ru.sbt.mipt.oop.models.events.SensorEvent;

public interface Manager {
    void loadData();
    void handleEvent(SensorEvent event);
}
