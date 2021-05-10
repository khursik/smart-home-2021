package ru.sbt.mipt.oop.handler.model.impl;

import ru.sbt.mipt.oop.handler.model.Manager;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmActivate;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmDeactivate;
import ru.sbt.mipt.oop.models.events.impl.alarm.AlarmEvent;

public class AlarmSystemManager implements Manager {
    private final Manager manager;
    private State state = new DeactivatedState();
    private String code;

    public AlarmSystemManager(Manager manager) {
        this.manager = manager;
    }

    void changeState(State state) {
        this.state = state;
    }

    @Override
    public void loadData() {
        manager.loadData();
    }

    @Override
    public void handleEvent(SensorEvent event) {
        //System.out.println(event);
        state.template(event);
    }


    abstract class State {
        void template(SensorEvent event) {
            if (isAlarmEvent(event)) {
                processAlarmEvent(event);
                return;
            }
            if (needToProcess()) {
                process(event);
            }
            if (needToSendSms()) {
                sendSms();
            }
        }

        boolean isAlarmEvent(SensorEvent event) {
            return event instanceof AlarmEvent;
        }

        void process(SensorEvent event) {}

        boolean needToProcess() {
            return true;
        }

        void processAlarmEvent(SensorEvent event) {}

        boolean needToSendSms() {
            return true;
        }

        void sendSms() {
            System.out.println("Sending sms");
        }

    }
    class DeactivatedState extends State {

        @Override
        boolean needToSendSms() {
            return false;
        }

        @Override
        void processAlarmEvent(SensorEvent event) {
            if (event instanceof AlarmActivate) {
                code = ((AlarmActivate) event).getCode();
                changeState(new ActivatedState());
            }
        }

        @Override
        void process(SensorEvent event) {
            manager.handleEvent(event);
        }
    }
    class ActivatedState extends State {
        @Override
        void processAlarmEvent(SensorEvent event) {
            if (event instanceof AlarmDeactivate) {
                if (code.equals(((AlarmDeactivate) event).getCode())) {
                    changeState(new DeactivatedState());
                    code = null;
                } else {
                    changeState(new AlarmState());
                }
            }
        }

        @Override
        void process(SensorEvent event) {
            changeState(new AlarmState());
        }
    }
    class AlarmState extends State {
        @Override
        boolean needToProcess() {
            return false;
        }

        @Override
        void processAlarmEvent(SensorEvent event) {
            if (event instanceof AlarmDeactivate) {
                if (code.equals(((AlarmDeactivate) event).getCode())) {
                    changeState(new DeactivatedState());
                    code = null;
                }
            }
        }
    }
}
