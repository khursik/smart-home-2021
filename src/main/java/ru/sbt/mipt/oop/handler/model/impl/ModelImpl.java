package ru.sbt.mipt.oop.handler.model.impl;

import ru.sbt.mipt.oop.handler.model.Model;
import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.data.Datasource;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.handler.view.Logger;
import ru.sbt.mipt.oop.models.SmartHome;
import ru.sbt.mipt.oop.models.events.SensorEvent;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
    private final Datasource datasource;
    private final Logger view;
    private final CommandSender commandSender;
    private final List<EventProcessor> processors;
    private SmartHome data;


    public ModelImpl(Datasource datasource, Logger view, CommandSender commandSender) {
        this.datasource = datasource;
        this.view = view;
        this.commandSender = commandSender;
        this.processors = new ArrayList<>();
    }

    @Override
    public void loadData() {
        data = datasource.getSmartHome();
    }

    @Override
    public void handleEvent(SensorEvent event) {
        view.printMessage("Got event: " + event);
        for (EventProcessor processor : processors) {
            String message = processor.process(event, data, commandSender);
            if (message != null) {
                view.printMessage(message);
            }
        }
    }

    public void addProcessor(EventProcessor processor) {
        processors.add(processor);
    }
}
