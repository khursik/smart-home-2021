package ru.sbt.mipt.oop.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.handler.model.data.Datasource;
import ru.sbt.mipt.oop.handler.model.data.JsonDatasource;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.handler.model.processor.impl.DoorClosedProcessor;
import ru.sbt.mipt.oop.handler.model.processor.impl.LightOffProcessor;
import ru.sbt.mipt.oop.models.SmartHome;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.DoorClosedEvent;
import ru.sbt.mipt.oop.models.events.impl.LightOffEvent;

import static org.junit.jupiter.api.Assertions.*;

class LightOffProcessorTest {

    @Test
    void process() {
        TestLogger logger = new TestLogger();
        TestCommandSender commandSender = new TestCommandSender();
        Datasource datasource = new JsonDatasource("output.json");
        SmartHome home = datasource.getSmartHome();
        SensorEvent event = new LightOffEvent("2");
        EventProcessor processor = new LightOffProcessor();
        processor.process(event, home, commandSender, logger);
        Assertions.assertEquals("Light 2 in room kitchen was turned off.", logger.cashedMessage);
    }
}