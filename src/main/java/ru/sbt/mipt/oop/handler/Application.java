package ru.sbt.mipt.oop.handler;

import ru.sbt.mipt.oop.handler.controller.Controller;
import ru.sbt.mipt.oop.handler.controller.impl.ControllerImpl;
import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.commands.sender.impl.ConsoleCommandSender;
import ru.sbt.mipt.oop.handler.model.data.Datasource;
import ru.sbt.mipt.oop.handler.model.data.JsonDatasource;
import ru.sbt.mipt.oop.handler.model.impl.ModelImpl;
import ru.sbt.mipt.oop.handler.model.processor.impl.DoorClosedProcessor;
import ru.sbt.mipt.oop.handler.model.processor.impl.DoorOpenProcessor;
import ru.sbt.mipt.oop.handler.model.processor.impl.LightOffProcessor;
import ru.sbt.mipt.oop.handler.model.processor.impl.LightOnProcessor;
import ru.sbt.mipt.oop.handler.view.ConsoleView;
import ru.sbt.mipt.oop.handler.view.View;
import ru.sbt.mipt.oop.models.events.impl.DoorClosedEvent;
import ru.sbt.mipt.oop.models.events.impl.DoorOpenEvent;
import ru.sbt.mipt.oop.models.events.impl.LightOffEvent;
import ru.sbt.mipt.oop.models.events.impl.LightOnEvent;

public class Application {

    public static void main(String... args) {
        Controller controller = configController();
        controller.refresh();
        // начинаем цикл обработки событий
        while (controller.generateEvent()) ;
    }

    private static Controller configController() {
        Datasource datasource = new JsonDatasource("output.json");
        CommandSender commandSender = new ConsoleCommandSender();
        //можно использовать di
        View view = new ConsoleView();

        ModelImpl model = new ModelImpl(datasource, view, commandSender);
        model.addProcessor(LightOnEvent.class, new LightOnProcessor());
        model.addProcessor(LightOffEvent.class, new LightOffProcessor());
        model.addProcessor(DoorClosedEvent.class, new DoorClosedProcessor());
        model.addProcessor(DoorOpenEvent.class, new DoorOpenProcessor());

        Controller controller = new ControllerImpl(model);
        return controller;
    }


}
