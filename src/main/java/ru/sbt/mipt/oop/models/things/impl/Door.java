package ru.sbt.mipt.oop.models.things.impl;

import ru.sbt.mipt.oop.models.things.Thing;

public class Door extends Thing {
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        super(id);
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
