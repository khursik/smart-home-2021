package ru.sbt.mipt.oop.handler.model.data;

import com.google.gson.*;
import ru.sbt.mipt.oop.models.Room;
import ru.sbt.mipt.oop.models.things.Thing;

import java.lang.reflect.Type;
import java.util.*;

public class SmartHomeDeserializer implements JsonDeserializer<Room> {

    @Override
    public Room deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        try {
            JsonObject room = jsonElement.getAsJsonObject();
        JsonObject mapJson = room.get("things").getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entries = mapJson.entrySet();
        Map<Class<? extends Thing>, List<Thing>> value = new HashMap<>();
        for(Map.Entry<String, JsonElement>entry:entries){
            Class childType = Class.forName(entry.getKey().split(" ")[1]);
            List<Thing>specified = new ArrayList<>();
            entry.getValue().getAsJsonArray().forEach((element)->{
                specified.add(context.deserialize(element, childType));
            });
                value.put(childType, specified);

        }
        return new Room(room.get("name").getAsString(), value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
