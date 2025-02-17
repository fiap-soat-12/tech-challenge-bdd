package br.com.fiap.tech_challenge.properties;

import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.ConfigFactory;

public class PropertiesConfigurationManager {

    private static final String ENVIRONMENT = "environment";

    public static PropertiesConfiguration getProperties() {
        setEnvironment();
        return ConfigCache.getOrCreate(PropertiesConfiguration.class);
    }

    private static void setEnvironment() {
        String environment = System.getProperty(ENVIRONMENT);
        String actualEnv = environment == null ? "prod" : environment;
        System.setProperty(ENVIRONMENT, actualEnv);
        ConfigFactory.setProperty(ENVIRONMENT, actualEnv);
    }
}