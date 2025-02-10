package br.com.fiap.tech_challenge.steps.config;

import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;

public class RestAssuredConfig {

    private static final Logger LOG = LoggerFactory.getLogger(RestAssuredConfig.class);

    @BeforeAll
    public static void setupRestAssured(){
        config();
    }

    private static void config() {
        LOG.info("Configurando RestAssured.");

        RestAssured.config = io.restassured.config.RestAssuredConfig.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL));

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
