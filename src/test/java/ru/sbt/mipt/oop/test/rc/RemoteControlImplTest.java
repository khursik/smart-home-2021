package ru.sbt.mipt.oop.test.rc;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.handler.Config;
import ru.sbt.mipt.oop.handler.controller.Controller;
import ru.sbt.mipt.oop.handler.controller.rc.RemoteControlImpl;
import ru.sbt.mipt.oop.handler.controller.rc.impl.*;
import ru.sbt.mipt.oop.handler.model.Manager;
import ru.sbt.mipt.oop.handler.model.impl.AlarmSystemManager;
import ru.sbt.mipt.oop.handler.model.impl.ManagerImpl;
import ru.sbt.mipt.oop.models.things.impl.Door;
import ru.sbt.mipt.oop.models.things.impl.Light;
import ru.sbt.mipt.oop.side.library.rc.RemoteControl;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RemoteControlImplTest {
    private ApplicationContext context;

    @BeforeAll
    public void setUp() {
        context = new AnnotationConfigApplicationContext(Config.class);
        Controller controller = context.getBean(Controller.class);
        controller.refresh();
    }

    @Test
    void testActivateAlarmCommand() {
        RemoteControl control = new RemoteControlImpl("385").
                registerCommand("A", context.getBean(ActivateAlarmCommand.class));
        control.onButtonPressed("A", "385");
        Assertions.assertTrue(context.getBean(AlarmSystemManager.class).getState() instanceof AlarmSystemManager.ActivatedState);
    }

    @Test
    void testCloseConcreteDoorCommand() {
        RemoteControl control = new RemoteControlImpl("385").
                registerCommand("A", context.getBean(CloseConcreteDoorCommand.class));
        control.onButtonPressed("A", "385");
        ((ManagerImpl) context.getBean(Manager.class)).getData().execute((thing, name) -> {
            if (thing.getId().equals("4") && thing instanceof Door) {
                Assertions.assertFalse(((Door) thing).isOpen());
            }
        });
    }

    @Test
    void testTurnOffLightsCommand() {
        RemoteControl control = new RemoteControlImpl("385").
                registerCommand("A", context.getBean(TurnOffLightsCommand.class));
        control.onButtonPressed("A", "385");
        ((ManagerImpl) context.getBean(Manager.class)).getData().execute((thing, name) -> {
            if (thing instanceof Light) {
                Assertions.assertFalse(((Light) thing).isOn());
            }
        });
    }

    @Test
    void testTurnOnAlarmCommand() {
        RemoteControl control = new RemoteControlImpl("385").
                registerCommand("A", context.getBean(TurnOnAlarmCommand.class));
        control.onButtonPressed("A", "385");
        Assertions.assertTrue(context.getBean(AlarmSystemManager.class).getState() instanceof AlarmSystemManager.AlarmState);
    }

    @Test
    void testTurnOnCorridorLights() {
        RemoteControl control = new RemoteControlImpl("385").
                registerCommand("A", context.getBean(TurnOnCorridorLightsCommand.class));
        control.onButtonPressed("A", "385");
        ((ManagerImpl) context.getBean(Manager.class)).getData().execute((thing, name) -> {
            if (thing instanceof Light && name.equals("corridor")) {
                Assertions.assertTrue(((Light) thing).isOn());
            }
        });
    }

    @Test
    void testTurnOnLightsCommand() {
        RemoteControl control = new RemoteControlImpl("385").
                registerCommand("A", context.getBean(TurnOnLightsCommand.class));
        control.onButtonPressed("A", "385");
        ((ManagerImpl) context.getBean(Manager.class)).getData().execute((thing, name) -> {
            if (thing instanceof Light) {
                Assertions.assertTrue(((Light) thing).isOn());
            }
        });
    }
}