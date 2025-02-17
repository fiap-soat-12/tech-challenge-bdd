package br.com.fiap.tech_challenge.properties;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:infra/${environment}.properties"})
public interface PropertiesConfiguration extends Config {

    @Key("env.baseUrl.order")
    String baseUrlOrder();

    @Key("env.baseUrl.cook")
    String baseUrlCook();

    @Key("env.baseUrl.payment")
    String baseUrlPayment();
}
