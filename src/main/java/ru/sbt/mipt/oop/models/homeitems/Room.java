package ru.sbt.mipt.oop.models.homeitems;

import ru.sbt.mipt.oop.models.Action;
import ru.sbt.mipt.oop.models.Actionable;
import ru.sbt.mipt.oop.models.HomeItem;

import java.util.*;

public class Room implements Actionable {
    private final HomeItemsList homeItems;
    private final String name;

    public Room(String name, Map<Class<? extends HomeItem>, List<HomeItem>> homeItems) {
        this.homeItems = new HomeItemsList(homeItems);
        this.name = name;
    }

    public Room(String name) {
        this.name = name;
        this.homeItems = new HomeItemsList(new HashMap<>());
    }

    public String getName() {
        return name;
    }

    public void addThing(HomeItem homeItem) {
        homeItems.addThing(homeItem);
    }

    public void addThings(List<HomeItem> concreteHomeItems) {
        for (HomeItem homeItem : concreteHomeItems) {
            addThing(homeItem);
        }
    }
    public void addThings(HomeItem... concreteHomeItems){
        addThings(Arrays.asList(concreteHomeItems));
    }

    @Override
    public void execute(Action action) {
        for (HomeItem homeItem : homeItems) {
            action.invoke(homeItem, name);
        }
    }
}
