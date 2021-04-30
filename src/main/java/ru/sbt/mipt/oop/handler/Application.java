package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.handler.controller.Controller;
import ru.sbt.mipt.oop.handler.controller.impl.ControllerImpl;
import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.commands.sender.impl.ConsoleCommandSender;
import ru.sbt.mipt.oop.handler.model.data.Datasource;
import ru.sbt.mipt.oop.handler.model.data.JsonDatasource;
import ru.sbt.mipt.oop.handler.model.impl.ModelImpl;
import ru.sbt.mipt.oop.handler.model.processor.impl.*;
import ru.sbt.mipt.oop.handler.view.LoggerImpl;
import ru.sbt.mipt.oop.handler.view.Logger;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.DoorClosedEvent;
import ru.sbt.mipt.oop.models.events.impl.DoorOpenEvent;
import ru.sbt.mipt.oop.models.events.impl.LightOffEvent;
import ru.sbt.mipt.oop.models.events.impl.LightOnEvent;

public class Application {

    public static void main(String... args) {
        Controller controller = configController();
        controller.refresh();
        // начинаем цикл обработки событий
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            controller.gotEvent(event);
            event = getNextSensorEvent();
        }
    }

    private static Controller configController() {
        Datasource datasource = new JsonDatasource("output.json");
        CommandSender commandSender = new ConsoleCommandSender();
        //можно использовать di
        Logger view = new LoggerImpl();

        ModelImpl model = new ModelImpl(datasource, view, commandSender);
        model.addProcessor(new LightOnProcessor());
        model.addProcessor(new LightOffProcessor());
        model.addProcessor(new DoorClosedProcessor());
        model.addProcessor(new DoorOpenProcessor());
        model.addProcessor(new HallDoorClosedProcessor());

        Controller controller = new ControllerImpl(model);
        return controller;

    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        String objectId = "" + ((int) (10 * Math.random()));
        switch ((int) (4 * Math.random())) {
            case 0:
                return new LightOnEvent(objectId);
            case 1:
                return new LightOffEvent(objectId);
            case 2:
                return new DoorClosedEvent(objectId);
            case 3:
                return new DoorOpenEvent(objectId);
        }
        return null;
    }


}
