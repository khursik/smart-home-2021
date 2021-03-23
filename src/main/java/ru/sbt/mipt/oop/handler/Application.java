package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.handler.controller.Controller;
import ru.sbt.mipt.oop.handler.controller.impl.ControllerImpl;
import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.commands.sender.impl.ConsoleCommandSender;
import ru.sbt.mipt.oop.handler.model.data.Datasource;
import ru.sbt.mipt.oop.handler.model.data.JsonDatasource;
import ru.sbt.mipt.oop.handler.model.impl.ManagerImpl;
import ru.sbt.mipt.oop.handler.model.processor.impl.*;
import ru.sbt.mipt.oop.handler.view.LoggerImpl;
import ru.sbt.mipt.oop.handler.view.Logger;

import static ru.sbt.mipt.oop.models.events.SensorEventProcessor.processEvent;


public class Application {

    public static void main(String... args) {
        Controller controller = configController();
        controller.refresh();

        processEvent(controller);
    }

    private static Controller configController() {
        Datasource datasource = new JsonDatasource("output.json");
        CommandSender commandSender = new ConsoleCommandSender();
        //можно использовать di
        Logger view = new LoggerImpl();

        ManagerImpl manager = new ManagerImpl(datasource, view, commandSender);
        manager.addProcessor(new LightOnProcessor());
        manager.addProcessor(new LightOffProcessor());
        manager.addProcessor(new DoorClosedProcessor());
        manager.addProcessor(new DoorOpenProcessor());
        manager.addProcessor(new HallDoorClosedProcessor());

        Controller controller = new ControllerImpl(manager);
        return controller;

    }

}
