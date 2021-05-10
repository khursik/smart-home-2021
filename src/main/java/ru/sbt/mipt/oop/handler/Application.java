package ru.sbt.mipt.oop.handler;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import ru.sbt.mipt.oop.handler.controller.Controller;
import ru.sbt.mipt.oop.models.events.generator.SensorEventGenerator;


public class Application {

    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Controller controller = context.getBean(Controller.class);
        controller.refresh();
        SensorEventGenerator sensorEventGenerator = context.getBean(SensorEventGenerator.class);
        sensorEventGenerator.start(controller);
    }

}
