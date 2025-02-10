package br.com.fiap.tech_challenge.common;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public abstract class BaseStep {

    final ScenarioContext context;

    final ContextAdapter contextAdapter = new ContextAdapter();

    protected ContextAdapter context() {
        return contextAdapter;
    }

    public class ContextAdapter {
        public void add(ScenarioContextEnum key, String value) {
            context.put(key.toString(), value);
        }

        public String getByKey(ScenarioContextEnum key) {
            return context.get(key.toString());
        }
    }
}
