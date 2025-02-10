package br.com.fiap.tech_challenge.steps;

import br.com.fiap.tech_challenge.common.BaseStep;
import br.com.fiap.tech_challenge.common.ScenarioContext;
import br.com.fiap.tech_challenge.common.ScenarioContextEnum;
import io.cucumber.java.pt.Entao;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ThenStep extends BaseStep {

    private final String baseUrl = "http://localhost:8357";

    public ThenStep(ScenarioContext context) {
        super(context);
    }

    @Entao("o pedido deve ser entregue a cozinha para sua produção e seu status deve ser PREPARING")
    public void o_pedido_deve_ser_entregue_a_cozinha_para_sua_producao_e_seu_status_deve_ser_PREPARING(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);

        Response response;
        response = given()
                .with()
                .contentType(ContentType.JSON)
                .with()
                .get(baseUrl + "/order/v1/orders/" + orderId);
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("orderId", notNullValue())
                .body("status", equalTo("PREPARING"));
    }

    @Entao("após o preparo, a cozinha deve evoluir o pedido para READY")
    public void apos_o_preparo_a_cozinha_deve_evoluir_o_pedido_para_READY(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);

        Response response;
        response = given()
                .with()
                .contentType(ContentType.JSON)
                .with()
                .get(baseUrl + "/cook/v1/orders/" + orderId);
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("orderId", notNullValue())
                .body("status", equalTo("READY"));

        response = given()
                .with()
                .contentType(ContentType.JSON)
                .with()
                .get(baseUrl + "/order/v1/orders/" + orderId);
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("orderId", notNullValue())
                .body("status", equalTo("READY"));
    }

    @Entao("após a retirada do pedido pelo cliente o pedido deve ser evoluido para o status FINISHED")
    public void apos_a_retirada_do_pedido_pelo_cliente_o_pedido_deve_ser_evoluido_para_o_status_FINISHED(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);

        Response response;
        response = given()
                .with()
                .contentType(ContentType.JSON)
                .with()
                .get(baseUrl + "/cook/v1/orders/" + orderId);
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("orderId", notNullValue())
                .body("status", equalTo("FINISHED"));

        response = given()
                .with()
                .contentType(ContentType.JSON)
                .with()
                .get(baseUrl + "/order/v1/orders/" + orderId);
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("orderId", notNullValue())
                .body("status", equalTo("FINISHED"));
    }
    @Entao("o pedido deve ser evoluido para o status FINISHED")
    public void o_pedido_deve_ser_evoluido_para_o_status_FINISHED(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);

        Response response;
        response = given()
                .with()
                .contentType(ContentType.JSON)
                .with()
                .get(baseUrl + "/order/v1/orders/" + orderId);
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("orderId", notNullValue())
                .body("status", equalTo("FINISHED"));
    }
}
