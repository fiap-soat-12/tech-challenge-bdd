package br.com.fiap.tech_challenge.steps;

import br.com.fiap.tech_challenge.common.BaseStep;
import br.com.fiap.tech_challenge.common.ScenarioContext;
import br.com.fiap.tech_challenge.common.ScenarioContextEnum;
import br.com.fiap.tech_challenge.util.Requests;
import io.cucumber.java.pt.Entao;
import org.apache.http.HttpStatus;

import static br.com.fiap.tech_challenge.common.Wait.await;
import static br.com.fiap.tech_challenge.constants.Constants.BodyAttributes.ORDER_ID;
import static br.com.fiap.tech_challenge.constants.Constants.BodyAttributes.STATUS;
import static br.com.fiap.tech_challenge.constants.Constants.EndpointConstants.COOK_BREADCRUMB_SLASH;
import static br.com.fiap.tech_challenge.constants.Constants.EndpointConstants.ORDER_BREADCRUMB_SLASH;
import static br.com.fiap.tech_challenge.constants.Constants.ParamTimes.FIVE_SECONDS;
import static br.com.fiap.tech_challenge.constants.Constants.StatusTypes.*;
import static br.com.fiap.tech_challenge.properties.PropertiesConfigurationManager.getProperties;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ThenStep extends BaseStep {

    private final String baseUrl = getProperties().baseUrl();
    private final Requests requests = new Requests();

    public ThenStep(ScenarioContext context) {
        super(context);
    }

    @Entao("o pedido deve ser entregue a cozinha para sua produção e seu status deve ser PREPARING")
    public void o_pedido_deve_ser_entregue_a_cozinha_para_sua_producao_e_seu_status_deve_ser_PREPARING(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);
        var orderStatusUrl = baseUrl + ORDER_BREADCRUMB_SLASH + orderId;

        requests.get(orderStatusUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(ORDER_ID, notNullValue())
                .body(STATUS, equalTo(PREPARING));
    }

    @Entao("após o preparo, a cozinha deve evoluir o pedido para READY")
    public void apos_o_preparo_a_cozinha_deve_evoluir_o_pedido_para_READY(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);
        var cookSetStatusUrl = baseUrl + COOK_BREADCRUMB_SLASH + orderId;
        var orderStatusUrl = baseUrl + ORDER_BREADCRUMB_SLASH + orderId;

        requests.put(cookSetStatusUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(ORDER_ID, notNullValue())
                .body(STATUS, equalTo(READY));

        await(FIVE_SECONDS);

        requests.get(orderStatusUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(ORDER_ID, notNullValue())
                .body(STATUS, equalTo(READY));
    }

    @Entao("após a retirada do pedido pelo cliente o pedido deve ser evoluido para o status FINISHED")
    public void apos_a_retirada_do_pedido_pelo_cliente_o_pedido_deve_ser_evoluido_para_o_status_FINISHED(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);
        var cookSetStatusUrl = baseUrl + COOK_BREADCRUMB_SLASH + orderId;
        var orderStatusUrl = baseUrl + ORDER_BREADCRUMB_SLASH + orderId;


        requests.put(cookSetStatusUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(ORDER_ID, notNullValue())
                .body(STATUS, equalTo(FINISHED));

        await(FIVE_SECONDS);

        requests.get(orderStatusUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(ORDER_ID, notNullValue())
                .body(STATUS, equalTo(FINISHED));
    }
    @Entao("o pedido deve ser evoluido para o status FINISHED")
    public void o_pedido_deve_ser_evoluido_para_o_status_FINISHED(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);
        var orderStatusUrl = baseUrl + ORDER_BREADCRUMB_SLASH + orderId;

        await(FIVE_SECONDS);

        requests.get(orderStatusUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(ORDER_ID, notNullValue())
                .body(STATUS, equalTo(FINISHED));
    }
}
