package ru.sbt.mipt.oop.handler.model.processor.impl;

import ru.sbt.mipt.oop.handler.model.commands.sender.CommandSender;
import ru.sbt.mipt.oop.handler.model.processor.EventProcessor;
import ru.sbt.mipt.oop.handler.view.Logger;
import ru.sbt.mipt.oop.models.events.SensorEvent;
import ru.sbt.mipt.oop.models.homeitems.SmartHome;

public class SendSMSProcessor implements EventProcessor {
    @Override
    public void process(SensorEvent event, SmartHome home, CommandSender commandSender, Logger logger) {
        System.out.println("Sending sms");
    }
}
