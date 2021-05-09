package ru.sbt.mipt.oop.handler.controller.rc;

public class SingleButtonPressedListener implements ButtonPressedListener{
    private final String buttonCode;
    private final Command toDoFunction;

    public SingleButtonPressedListener(String buttonCode, Command command) {
        this.buttonCode = buttonCode;
        this.toDoFunction = command;
    }

    @Override
    public boolean onPressed(String buttonCode) {
        if (this.buttonCode.equals(buttonCode)) {
            toDoFunction.execute();
            return true;
        }
        return false;
    }
}
