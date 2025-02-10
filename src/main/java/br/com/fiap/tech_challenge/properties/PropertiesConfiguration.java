package br.com.fiap.tech_challenge.properties;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:infra/${environment}.properties"})
public interface PropertiesConfiguration extends Config {

    @Key("env.baseUrl")
    String baseUrl();
}
