package org.example.reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.composite.ProjectComponent;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    private static final Logger logger = LogManager.getLogger();
    private final Gson gson;

    public JsonReader() {
        gson = new GsonBuilder()
                .registerTypeAdapter(ProjectComponent.class, new ProjectComponentAdapter())
                .create();
    }

    public List<ProjectComponent> readComponentsFromFile(String fileName) throws IOException {
        logger.info("Reading components from file: " + fileName);
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                logger.error("File not found: " + fileName);
                throw new IOException("File not found: " + fileName);
            }

            try (Reader reader = new InputStreamReader(inputStream)) {
                Type listType = new TypeToken<List<JsonObject>>() {}.getType();
                List<JsonObject> data = gson.fromJson(reader, listType);

                List<ProjectComponent> components = new ArrayList<>();
                for (JsonObject jsonObject : data) {
                    components.add(parseComponent(jsonObject));
                }
                logger.info("Successfully read components from file.");
                return components;
            }
        } catch (IOException e) {
            logger.error( "Error reading from file: " + fileName, e);
            throw e;
        }
    }

    private ProjectComponent parseComponent(JsonObject jsonObject) {
        return gson.fromJson(jsonObject, ProjectComponent.class);
    }
}
