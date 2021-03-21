package ru.sbt.mipt.oop.models.things;

import java.util.*;

public class ThingsList implements Iterable<Thing> {
    private final Map<Class<? extends Thing>, List<Thing>> things;

    public ThingsList(Map<Class<? extends Thing>, List<Thing>> things) {
        this.things = things;
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

    @Override
    public Iterator<Thing> iterator() {
        return new ThingsIterator();
    }
    class ThingsIterator implements Iterator<Thing> {
        private final Iterator<Class<? extends Thing>> keysIterator = things.keySet().iterator();
        private Class<? extends Thing> key = keysIterator.next();
        private int index = -1;
        @Override
        public boolean hasNext() {
            if (keysIterator.hasNext()) {
                return true;
            } else {
                if (index < things.get(key).size() - 1) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Thing next() {
            if (index < things.get(key).size() - 1) {
                index++;
                return things.get(key).get(index);
            } else if (index == things.get(key).size() - 1) {
                index = 0;
                key = keysIterator.next();
                return things.get(key).get(index);
            }
            return null;
        }
    }
}

