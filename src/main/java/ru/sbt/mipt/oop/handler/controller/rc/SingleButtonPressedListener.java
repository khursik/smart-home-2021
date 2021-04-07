package ru.sbt.mipt.oop.handler.controller.rc;

import java.util.function.Function;

public class SingleButtonPressedListener implements ButtonPressedListener{
    private final String buttonCode;
    private final Code toDoFunction;

    public SingleButtonPressedListener(String buttonCode, Code toDoFunction) {
        this.buttonCode = buttonCode;
        this.toDoFunction = toDoFunction;
    }

    @Override
    public boolean onPressed(String buttonCode) {
        if (this.buttonCode.equals(buttonCode)) {
            toDoFunction.invoke();
            return true;
        }
        return false;
    }
}
