package ru.sbt.mipt.oop.models.homeitems;

import ru.sbt.mipt.oop.models.HomeItem;

import java.util.*;

public class HomeItemsList implements Iterable<HomeItem> {
    private final Map<Class<? extends HomeItem>, List<HomeItem>> homeItems;

    public HomeItemsList(Map<Class<? extends HomeItem>, List<HomeItem>> homeItems) {
        this.homeItems = homeItems;
    }

    public void addThing(HomeItem homeItem) {
        Class<? extends HomeItem> type = homeItem.getClass();
        if (homeItems.containsKey(type)) {
            homeItems.get(type).add(homeItem);
        } else {
            List<HomeItem> list = new ArrayList<>();
            list.add(homeItem);
            homeItems.put(type, list);
        }
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
    public Iterator<HomeItem> iterator() {
        return new ThingsIterator();
    }
    class ThingsIterator implements Iterator<HomeItem> {
        private final Iterator<Class<? extends HomeItem>> keysIterator = homeItems.keySet().iterator();
        private Class<? extends HomeItem> key = keysIterator.next();
        private int index = -1;
        @Override
        public boolean hasNext() {
            if (keysIterator.hasNext()) {
                return true;
            } else {
                if (index < homeItems.get(key).size() - 1) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public HomeItem next() {
            if (index < homeItems.get(key).size() - 1) {
                index++;
                return homeItems.get(key).get(index);
            } else if (index == homeItems.get(key).size() - 1) {
                index = 0;
                key = keysIterator.next();
                return homeItems.get(key).get(index);
            }
            return null;
        }
    }
}

