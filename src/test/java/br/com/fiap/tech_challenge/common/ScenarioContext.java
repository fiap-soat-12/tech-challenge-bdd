package br.com.fiap.tech_challenge.common;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScenarioContext {

    final Map<String, String> context;

    public ScenarioContext() {
        context = new HashMap<>();
    }

    public void put(String key, String value){
        context.put(key, value);
    }

    public String get(String key) {
        return context.get(key);
    }
}
