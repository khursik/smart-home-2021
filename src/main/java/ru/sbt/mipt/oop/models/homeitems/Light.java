package ru.sbt.mipt.oop.models.homeitems;


import ru.sbt.mipt.oop.models.HomeItem;

public class Light extends HomeItem {
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
