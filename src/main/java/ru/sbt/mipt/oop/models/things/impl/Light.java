package ru.sbt.mipt.oop.models.things.impl;

import ru.sbt.mipt.oop.models.things.Thing;

public class Light extends Thing {
    private boolean isOn;
    public Light(String id, boolean isOn) {
        super(id);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
