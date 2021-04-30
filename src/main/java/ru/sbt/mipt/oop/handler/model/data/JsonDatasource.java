package ru.sbt.mipt.oop.handler.model.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.models.homeitems.Room;
import ru.sbt.mipt.oop.models.homeitems.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonDatasource implements Datasource{
    final String path;

    public JsonDatasource(String path) {
        this.path = path;
    }

    @Override
    public SmartHome getSmartHome() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Room.class,new SmartHomeDeserializer()).create();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(path)));
            return gson.fromJson(json, SmartHome.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
