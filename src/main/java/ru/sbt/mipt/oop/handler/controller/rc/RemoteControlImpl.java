package ru.sbt.mipt.oop.handler.controller.rc;

import ru.sbt.mipt.oop.side.library.rc.RemoteControl;

import java.util.ArrayList;
import java.util.List;

public class RemoteControlImpl implements RemoteControl {
    private final String rcId;
    private final List<ButtonPressedListener> listeners = new ArrayList<>();

    public RemoteControlImpl(String rcId) {
        this.rcId = rcId;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (this.rcId.equals(rcId)) {
            for (ButtonPressedListener listener : listeners) {
                if (listener.onPressed(buttonCode)) {
                    break;
                }
            }
        }
    }

    public RemoteControlImpl registerListener(ButtonPressedListener buttonPressedListener) {
        listeners.add(buttonPressedListener);
        return this;
    }
}
