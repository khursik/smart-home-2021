package ru.sbt.mipt.oop.handler.model.impl;

import ru.sbt.mipt.oop.handler.model.Model;
import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.data.Datasource;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.handler.view.View;
import ru.sbt.mipt.oop.models.SmartHome;
import ru.sbt.mipt.oop.models.events.SensorEvent;

import java.util.HashMap;
import java.util.Map;

public class ModelImpl implements Model {
    private final Datasource datasource;
    private final View view;
    private final CommandSender commandSender;
    private final Map<Class<? extends SensorEvent>, EventProcessor> processors;
    private SmartHome data;


    public ModelImpl(Datasource datasource, View view, CommandSender commandSender) {
        this.datasource = datasource;
        this.view = view;
        this.commandSender = commandSender;
        this.processors = new HashMap<>();
    }

    @Override
    public void loadData() {
        data = datasource.getSmartHome();
    }

    @Override
    public void handleEvent(SensorEvent event) {
        view.addMessage("Got event: " + event);
        view.addMessage(processors.get(event.getClass()).process(event, data, commandSender));

    }

    public void addProcessor(Class<? extends SensorEvent> eventType, EventProcessor processor) {
        processors.put(eventType, processor);
    }
}
