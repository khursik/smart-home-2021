package ru.sbt.mipt.oop.generator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.models.homeitems.Room;
import ru.sbt.mipt.oop.models.homeitems.SmartHome;
import ru.sbt.mipt.oop.models.homeitems.Door;
import ru.sbt.mipt.oop.models.homeitems.Light;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class HomeBuilder {

    public static void main(String[] args) throws IOException {
        Room kitchen = new Room("kitchen");
        kitchen.addThings(
                new Light("1", false),
                new Light("2", true),
                new Door(false, "1"));

        Room bathroom = new Room("bathroom");
        bathroom.addThings(
                new Light("3", true),
                new Door(false, "2"));

        Room bedroom = new Room("bedroom");
        bedroom.addThings(
                new Light("4", false),
                new Light("5", false),
                new Light("6", false),
                new Door(true, "3"));

        Room hall = new Room("hall");
        hall.addThings(
                new Light("7", false),
                new Light("8", false),
                new Light("9", false),
                new Door(false, "4"));

        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get("output.json");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }

}
