package ru.sbt.mipt.oop.models.homeitems;


import ru.sbt.mipt.oop.models.HomeItem;

public class Door extends HomeItem {
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        super(id);
        this.isOpen = isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
