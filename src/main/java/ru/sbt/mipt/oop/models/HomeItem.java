package ru.sbt.mipt.oop.models;

public abstract class HomeItem {
    private final String id;

    public HomeItem(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
