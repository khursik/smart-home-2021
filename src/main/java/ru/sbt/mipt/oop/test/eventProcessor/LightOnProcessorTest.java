package ru.sbt.mipt.oop.test.eventProcessor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.handler.model.data.Datasource;
import ru.sbt.mipt.oop.handler.model.data.JsonDatasource;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.handler.model.processor.impl.LightOnProcessor;
import ru.sbt.mipt.oop.models.SmartHome;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.LightOnEvent;

class LightOnProcessorTest {

    @Test
    void process() {
        TestLogger logger = new TestLogger();
        TestCommandSender commandSender = new TestCommandSender();
        Datasource datasource = new JsonDatasource("output.json");
        SmartHome home = datasource.getSmartHome();
        SensorEvent event = new LightOnEvent("2");
        EventProcessor processor = new LightOnProcessor();
        processor.process(event, home, commandSender, logger);
        Assertions.assertEquals("Light 2 in room kitchen was turned on.", logger.cashedMessage);
    }
}