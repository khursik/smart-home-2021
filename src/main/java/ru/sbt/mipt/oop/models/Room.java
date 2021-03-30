package ru.sbt.mipt.oop.models;

import ru.sbt.mipt.oop.models.things.Thing;

import java.util.*;

public class Room {
    private final Map<Class<? extends Thing>, List<Thing>> things;
    private final String name;

    public Room(String name, Map<Class<? extends Thing>, List<Thing>> things) {
        this.things = things;
        this.name = name;
    }

    public Room(String name) {
        this.name = name;
        this.things = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<Class<? extends Thing>, List<Thing>> getThings() {
        return things;
    }

    public List<Thing> getSpecified(Class<? extends Thing> type){
        return things.get(type);
    }

    public void addThing(Thing thing) {
        Class<? extends Thing> type = thing.getClass();
        if (things.containsKey(type)) {
            things.get(type).add(thing);
        } else {
            List<Thing> list = new ArrayList<>();
            list.add(thing);
            things.put(type, list);
        }
    }

    public void addThings(List<Thing> concreteThings) {
        for (Thing thing : concreteThings) {
            addThing(thing);
        }
    }
    public void addThings(Thing... concreteThings){
        addThings(Arrays.asList(concreteThings));
    }
}
