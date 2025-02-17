package br.com.fiap.tech_challenge.common;

public abstract class BaseStep {

    private final ScenarioContext context;

    private final ContextAdapter contextAdapter = new ContextAdapter();

    protected BaseStep(ScenarioContext context) {
        this.context = context;
    }

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
