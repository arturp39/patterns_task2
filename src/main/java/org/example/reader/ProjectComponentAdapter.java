package org.example.reader;

import com.google.gson.*;
import org.example.composite.ProjectComponent;
import org.example.composite.Status;
import org.example.factory.ProjectFactory;
import org.example.factory.impl.ProjectFactoryImpl;

import java.lang.reflect.Type;
import java.util.Date;

public class ProjectComponentAdapter implements JsonDeserializer<ProjectComponent> {
    private ProjectFactory projectFactory;

    public ProjectComponentAdapter() {
        this.projectFactory = new ProjectFactoryImpl();
    }

    @Override
    public ProjectComponent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String componentType = jsonObject.get("type").getAsString();

        if ("project".equalsIgnoreCase(componentType)) {
            String name = jsonObject.get("name").getAsString();
            String description = jsonObject.get("description").getAsString();
            return projectFactory.createProject(name, description);
        } else if ("task".equalsIgnoreCase(componentType)) {
            String name = jsonObject.get("name").getAsString();
            String description = jsonObject.get("description").getAsString();
            Status status = Status.valueOf(jsonObject.get("status").getAsString());
            Date deadline = context.deserialize(jsonObject.get("deadline"), Date.class);
            return projectFactory.createTask(name, description, status, deadline);
        }

        throw new JsonParseException("Unknown element type: " + componentType);
    }
}
