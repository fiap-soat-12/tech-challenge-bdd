package br.com.fiap.tech_challenge.steps;

import br.com.fiap.tech_challenge.common.BaseStep;
import br.com.fiap.tech_challenge.common.ScenarioContext;
import br.com.fiap.tech_challenge.common.ScenarioContextEnum;
import io.cucumber.java.pt.Dado;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GivenStep extends BaseStep {

    private final String baseUrl = "http://localhost:8357";

    public GivenStep(ScenarioContext context) {
        super(context);
    }

    @Dado("que um cliente cria um pedido")
    public void que_um_cliente_cria_um_pedido(){
        Response response;
        response = given()
                .with()
                .body(createOrder().toString())
                .contentType(ContentType.JSON)
                .with()
                .post(baseUrl + "/order/v1/orders");
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("orderId", notNullValue())
                .body("sequence", notNullValue());

        var orderId = response.jsonPath().getString("orderId");

        context().add(ScenarioContextEnum.ORDER_ID, orderId);
    }

    private JSONObject createOrder(){
        return new JSONObject()
                .put("customerId", "3d6fa6be-9dfb-4e51-b4d8-08f9351a2f17")
                .put("products",
                        List.of(
                                new JSONObject()
                                        .put("id", "bf00ac4a-dd15-4693-8dde-a20e8babf993")
                                        .put("observation", "sem queijo"),
                                new JSONObject()
                                        .put("id", "bf00ac4a-dd15-4693-8dde-a20e8babf993")
                                        .put("observation", "sem queijo"),
                                new JSONObject()
                                        .put("id", "bf00ac4a-dd15-4693-8dde-a20e8babf993")
                                        .put("observation", "sem queijo"))
                );
    }
}
