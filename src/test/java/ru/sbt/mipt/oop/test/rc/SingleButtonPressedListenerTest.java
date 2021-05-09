package ru.sbt.mipt.oop.test.rc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.handler.controller.rc.ButtonPressedListener;
import ru.sbt.mipt.oop.handler.controller.rc.SingleButtonPressedListener;

import java.util.concurrent.atomic.AtomicReference;

class SingleButtonPressedListenerTest {

    @Test
    void testOnPressedCorrect() {
        AtomicReference<Integer> a = new AtomicReference<>(0);
        ButtonPressedListener buttonPressedListener = new SingleButtonPressedListener("A", () -> {a.getAndSet(a.get() + 1);});
        Assertions.assertTrue(buttonPressedListener.onPressed("A"));
        Assertions.assertEquals( 1, a.get().intValue());
    }
    @Test
    void testOnPressedIncorrect() {
        AtomicReference<Integer> a = new AtomicReference<>(0);
        ButtonPressedListener buttonPressedListener = new SingleButtonPressedListener("A", () -> {a.getAndSet(a.get() + 1);});
        Assertions.assertFalse(buttonPressedListener.onPressed("B"));
        Assertions.assertEquals( 0, a.get().intValue());
    }
}