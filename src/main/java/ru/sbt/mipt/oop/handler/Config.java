package ru.sbt.mipt.oop.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.handler.controller.Controller;
import ru.sbt.mipt.oop.handler.controller.impl.ControllerImpl;
import ru.sbt.mipt.oop.handler.model.Manager;
import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.commands.sender.impl.ConsoleCommandSender;
import ru.sbt.mipt.oop.handler.model.data.Datasource;
import ru.sbt.mipt.oop.handler.model.data.JsonDatasource;
import ru.sbt.mipt.oop.handler.model.impl.AlarmSystemManager;
import ru.sbt.mipt.oop.handler.model.impl.ManagerImpl;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.handler.model.processor.impl.*;
import ru.sbt.mipt.oop.handler.view.Logger;
import ru.sbt.mipt.oop.handler.view.LoggerImpl;
import ru.sbt.mipt.oop.models.events.generator.SensorEventGenerator;
import ru.sbt.mipt.oop.models.events.generator.impl.SensorEventManagerAdapter;

import java.util.Collection;

@Configuration
public class Config {
    @Bean
    CommandSender commandSender() {
        return new ConsoleCommandSender();
    }
    @Bean
    SensorEventGenerator sensorEventGenerator() {
        return new SensorEventManagerAdapter();
    }
    @Bean
    Manager manager(Collection<EventProcessor> eventProcessors, Datasource datasource, Logger logger, CommandSender commandSender) {
        ManagerImpl manager = new ManagerImpl(datasource, logger, commandSender);
        for (EventProcessor eventProcessor : eventProcessors) {
            manager.addProcessor(eventProcessor);
        }
        return manager;
    }
    @Bean
    Logger logger() {
        return new LoggerImpl();
    }
    @Bean
    Controller controller(Manager alarmSystemManager) {
        return new ControllerImpl(alarmSystemManager);
    }
    @Bean
    Manager alarmSystemManager(Manager manager) {
        return new AlarmSystemManager(manager);
    }
    @Bean
    Datasource datasource() {
        return new JsonDatasource("output.json");
    }
    @Bean
    EventProcessor doorClosedProcessor() {
        return new DoorClosedProcessor();
    }
    @Bean
    EventProcessor doorOpenProcessor() {
        return new DoorOpenProcessor();
    }
    @Bean
    EventProcessor hallDoorClosedProcessor() {
        return new HallDoorClosedProcessor();
    }
    @Bean
    EventProcessor lightOffProcessor() {
        return new LightOffProcessor();
    }
    @Bean
    EventProcessor lightonProcessor() {
        return new LightOnProcessor();
    }

}
