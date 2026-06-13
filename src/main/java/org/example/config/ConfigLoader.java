package org.example.config;

import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.io.InputStream;

public class ConfigLoader<T> {
    private final Class<T> type;
    private T config;
    private final JsonMapper jsonMapper;

    public ConfigLoader(Class<T> type) {
        this.type = type;
        this.jsonMapper = new JsonMapper();
    }

    public void loadFromFile(String fileName) throws IOException {
        try ( InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            config = jsonMapper.readValue(is, type);
        }
    }

    public T getConfig(){
        return this.config;
    }
}

