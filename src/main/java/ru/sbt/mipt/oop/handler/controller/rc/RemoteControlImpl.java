package ru.sbt.mipt.oop.handler.controller.rc;

import ru.sbt.mipt.oop.side.library.rc.RemoteControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteControlImpl implements RemoteControl {
    private final String rcId;
    //private final List<ButtonPressedListener> listeners = new ArrayList<>();
    private final Map<String, Command> buttonCodeToCommandMap;

    public RemoteControlImpl(String rcId) {
        this.rcId = rcId;
        this.buttonCodeToCommandMap = new HashMap<>();
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (this.rcId.equals(rcId)) {
/*            for (ButtonPressedListener listener : listeners) {
                if (listener.onPressed(buttonCode)) {
                    break;
                }
            }*/
            if (buttonCodeToCommandMap.containsKey(buttonCode)) {
                buttonCodeToCommandMap.get(buttonCode).execute();
            }
        }
    }

/*    public RemoteControlImpl registerListener(ButtonPressedListener buttonPressedListener) {
        listeners.add(buttonPressedListener);
        return this;
    }*/
    public RemoteControl registerCommand(String buttonCode, Command command) {
        buttonCodeToCommandMap.put(buttonCode, command);
        return this;
    }
}
