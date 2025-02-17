package br.com.fiap.tech_challenge.common;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private final Map<String, String> context;

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
