package ru.sbt.mipt.oop.handler.model.commands;

public abstract class SensorCommand {
    private final String objectId;

    public SensorCommand(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "SensorCommand{" +
                "type=" + this.getClass().getSimpleName() +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
