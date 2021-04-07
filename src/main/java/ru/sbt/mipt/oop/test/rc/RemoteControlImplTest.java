package ru.sbt.mipt.oop.test.rc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.handler.controller.rc.ButtonPressedListener;
import ru.sbt.mipt.oop.handler.controller.rc.RemoteControlImpl;
import ru.sbt.mipt.oop.handler.controller.rc.SingleButtonPressedListener;
import ru.sbt.mipt.oop.side.library.rc.RemoteControl;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class RemoteControlImplTest {

    @Test
    void testOnButtonPressedIncorrectButtonCode() {
        AtomicReference<Integer> a = new AtomicReference<>(0);
        ButtonPressedListener buttonPressedListener = new SingleButtonPressedListener("A", () -> {a.getAndSet(a.get() + 1);});
        RemoteControl control = new RemoteControlImpl("385").registerListener(buttonPressedListener);
        control.onButtonPressed("D", "385");
        Assertions.assertEquals( 0, a.get().intValue());
    }

    @Test
    void testOnButtonPressedIncorrectRcId() {
        AtomicReference<Integer> a = new AtomicReference<>(0);
        ButtonPressedListener buttonPressedListener = new SingleButtonPressedListener("A", () -> {a.getAndSet(a.get() + 1);});
        RemoteControl control = new RemoteControlImpl("385").registerListener(buttonPressedListener);
        control.onButtonPressed("A", "384");
        Assertions.assertEquals( 0, a.get().intValue());
    }

    @Test
    void testOnButtonPressedCorrectBoth() {
        AtomicReference<Integer> a = new AtomicReference<>(0);
        ButtonPressedListener buttonPressedListener = new SingleButtonPressedListener("A", () -> {a.getAndSet(a.get() + 1);});
        RemoteControl control = new RemoteControlImpl("385").registerListener(buttonPressedListener);
        control.onButtonPressed("A", "385");
        Assertions.assertEquals( 1, a.get().intValue());
    }
}