package ru.sbt.mipt.oop.models.events.impl.alarm;

import ru.sbt.mipt.oop.models.events.SensorEvent;

public abstract class AlarmEvent extends SensorEvent {
    private final String code;

    public AlarmEvent(String code) {
        super(null);
        this.code = code;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + this.getClass().toString() +
                ", code='" + code + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }
}
