package ru.sbt.mipt.oop.models.things;

public abstract class Thing {
    private final String id;

    public Thing(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
