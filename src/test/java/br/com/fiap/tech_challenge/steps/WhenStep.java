package br.com.fiap.tech_challenge.steps;

import br.com.fiap.tech_challenge.common.BaseStep;
import br.com.fiap.tech_challenge.common.ScenarioContext;
import br.com.fiap.tech_challenge.common.ScenarioContextEnum;
import br.com.fiap.tech_challenge.common.Wait;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class WhenStep extends BaseStep {

    private final String baseUrl = "http://localhost:8357";

    public WhenStep(ScenarioContext context) {
        super(context);
    }

    @Quando("o pagamento do pedido é confirmado")
    public void o_pagamento_do_pedido_e_confirmado(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);

        Response response;
        response = given()
                .with()
                .body(confirmPayment(orderId).toString())
                .contentType(ContentType.JSON)
                .with()
                .get(baseUrl + "/order/v1/orders/" + orderId + "/paid-status");
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo("false"));

        response = given()
                .with()
                .body(confirmPayment(orderId).toString())
                .contentType(ContentType.JSON)
                .with()
                .put(baseUrl + "/order/v1/orders");
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        Wait.await(5000L);

        response = given()
                .with()
                .body(confirmPayment(orderId).toString())
                .contentType(ContentType.JSON)
                .with()
                .get(baseUrl + "/order/v1/orders/" + orderId + "/paid-status");
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo("true"));
    }

    @Quando("o pagamento do pedido é recusado")
    public void o_pagamento_do_pedido_e_recusado (){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);

        Response response;
        response = given()
                .with()
                .body(refusePayment(orderId).toString())
                .contentType(ContentType.JSON)
                .with()
                .put(baseUrl + "/order/v1/orders");
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        Wait.await(5000L);

        response = given()
                .with()
                .body(confirmPayment(orderId).toString())
                .contentType(ContentType.JSON)
                .with()
                .get(baseUrl + "/order/v1/orders/" + orderId + "/paid-status");
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo("false"));
    }

    private JSONObject confirmPayment(String orderId) {
        return new JSONObject()
                .put("orderId", orderId)
                .put("isPaid", true);
    }

    private JSONObject refusePayment(String orderId) {
        return new JSONObject()
                .put("orderId", orderId)
                .put("isPaid", false);
    }
}
