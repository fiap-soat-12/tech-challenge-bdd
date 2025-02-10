package br.com.fiap.tech_challenge.steps.config;

import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;

import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;

public class RestAssuredConfig {

    @BeforeAll
    public static void setupRestAssured(){
        config();
    }

    private static void config() {
        RestAssured.config = io.restassured.config.RestAssuredConfig.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL));
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
