package br.com.fiap.tech_challenge.util;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class Requests {

    public ValidatableResponse get(String url) {
        return given()
                .with()
                .contentType(ContentType.JSON)
                .with()
                .get(url)
                .then()
                .assertThat();
    }

    public ValidatableResponse put(String url) {
        return given()
                .with()
                .contentType(ContentType.JSON)
                .with()
                .put(url)
                .then()
                .assertThat();
    }

    public ValidatableResponse put(String url, String body) {
        return given()
                .with()
                .body(body)
                .contentType(ContentType.JSON)
                .with()
                .put(url)
                .then()
                .assertThat();
    }

    public Response post(String url, String body) {
        return given()
                .with()
                .body(body)
                .contentType(ContentType.JSON)
                .with()
                .post(url);
    }

}
