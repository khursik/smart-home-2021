package ru.sbt.mipt.oop.models;

import ru.sbt.mipt.oop.models.things.Action;
import ru.sbt.mipt.oop.models.things.Actionable;
import ru.sbt.mipt.oop.models.things.Thing;
import ru.sbt.mipt.oop.models.things.ThingsList;

import java.util.*;

public class Room implements Actionable {
    private final ThingsList things;
    private final String name;

    public Room(String name, Map<Class<? extends Thing>, List<Thing>> things) {
        this.things = new ThingsList(things);
        this.name = name;
    }

    public Room(String name) {
        this.name = name;
        this.things = new ThingsList(new HashMap<>());
    }

    public String getName() {
        return name;
    }

    public List<Thing> getSpecified(Class<? extends Thing> type){
        return things.getSpecified(type);
    }

    public void addThing(Thing thing) {
        things.addThing(thing);
    }

    public void addThings(List<Thing> concreteThings) {
        for (Thing thing : concreteThings) {
            addThing(thing);
        }
    }
    public void addThings(Thing... concreteThings){
        addThings(Arrays.asList(concreteThings));
    }

    @Override
    public void execute(Action action) {
        for (Thing thing : things) {
            action.invoke(thing, name);
        }
    }
}
