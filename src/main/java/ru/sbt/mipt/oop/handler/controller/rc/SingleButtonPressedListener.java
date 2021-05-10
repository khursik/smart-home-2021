package ru.sbt.mipt.oop.handler.controller.rc;

public class SingleButtonPressedListener implements ButtonPressedListener {
    private final String buttonCode;
    private final Command command;

    public SingleButtonPressedListener(String buttonCode, Command command) {
        this.buttonCode = buttonCode;
        this.command = command;
    }

    @Override
    public boolean onPressed(String buttonCode) {
        if (this.buttonCode.equals(buttonCode)) {
            command.execute();
            return true;
        }
        return false;
    }
}
