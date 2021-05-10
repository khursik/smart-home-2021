package ru.sbt.mipt.oop.models.events;

public abstract class SensorEvent {
    private final String objectId;

    public SensorEvent(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + this.getClass().toString() +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
