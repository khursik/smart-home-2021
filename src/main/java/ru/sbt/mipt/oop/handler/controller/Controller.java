package ru.sbt.mipt.oop.handler.controller;

import ru.sbt.mipt.oop.models.events.SensorEvent;

public interface Controller {
    void gotEvent(SensorEvent event);
    void refresh();
}
