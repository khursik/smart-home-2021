package ru.sbt.mipt.oop.test.eventProcessor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.handler.model.data.Datasource;
import ru.sbt.mipt.oop.handler.model.data.JsonDatasource;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.handler.model.processor.impl.DoorClosedProcessor;
import ru.sbt.mipt.oop.models.SmartHome;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.DoorClosedEvent;

class DoorClosedProcessorTest {

    @Test
    void testprocess() {
        TestLogger logger = new TestLogger();
        TestCommandSender commandSender = new TestCommandSender();
        Datasource datasource = new JsonDatasource("output.json");
        SmartHome home = datasource.getSmartHome();
        SensorEvent event = new DoorClosedEvent("1");
        EventProcessor processor = new DoorClosedProcessor();
        processor.process(event, home, commandSender, logger);
        Assertions.assertEquals("Door 1 in room kitchen was closed.", logger.cashedMessage);
    }

}