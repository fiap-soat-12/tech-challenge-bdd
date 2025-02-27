package br.com.fiap.tech_challenge.steps;

import br.com.fiap.tech_challenge.common.BaseStep;
import br.com.fiap.tech_challenge.common.ScenarioContext;
import br.com.fiap.tech_challenge.common.ScenarioContextEnum;
import br.com.fiap.tech_challenge.util.Requests;
import io.cucumber.java.pt.Entao;
import org.apache.http.HttpStatus;

import static br.com.fiap.tech_challenge.common.Wait.await;
import static br.com.fiap.tech_challenge.constants.Constants.BodyAttributesConstants.ORDER_ID;
import static br.com.fiap.tech_challenge.constants.Constants.BodyAttributesConstants.STATUS;
import static br.com.fiap.tech_challenge.constants.Constants.EndpointConstants.*;
import static br.com.fiap.tech_challenge.constants.Constants.TimesConstants.FIVE_SECONDS;
import static br.com.fiap.tech_challenge.constants.Constants.StatusTypesConstants.*;
import static br.com.fiap.tech_challenge.properties.PropertiesConfigurationManager.getProperties;
import static org.hamcrest.Matchers.equalTo;

public class ThenStep extends BaseStep {

    private final String baseUrlOrder = getProperties().baseUrlOrder();
    private final String baseUrlCook = getProperties().baseUrlCook();
    private final Requests requests = new Requests();

    public ThenStep(ScenarioContext context) {
        super(context);
    }

    @Entao("o pedido deve ser entregue a cozinha para sua produção e seu status deve ser PREPARING")
    public void o_pedido_deve_ser_entregue_a_cozinha_para_sua_producao_e_seu_status_deve_ser_PREPARING(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);
        var orderStatusUrl = baseUrlOrder + ORDER_BREADCRUMB_SLASH + orderId;

        requests.get(orderStatusUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(ORDER_ID, equalTo(orderId))
                .body(STATUS, equalTo(PREPARING));
    }

    @Entao("após o preparo, a cozinha deve evoluir o pedido para READY")
    public void apos_o_preparo_a_cozinha_deve_evoluir_o_pedido_para_READY(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);
        var cookSetStatusUrl = baseUrlCook + COOK_BREADCRUMB_SLASH + orderId + COOK_STATUS_READY;
        var orderStatusUrl = baseUrlOrder + ORDER_BREADCRUMB_SLASH + orderId;

        requests.patch(cookSetStatusUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(ORDER_ID, equalTo(orderId))
                .body(STATUS, equalTo(READY));

        await(FIVE_SECONDS);

        requests.get(orderStatusUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(ORDER_ID, equalTo(orderId))
                .body(STATUS, equalTo(READY));
    }

    @Entao("após a retirada do pedido pelo cliente o pedido deve ser evoluido para o status FINISHED")
    public void apos_a_retirada_do_pedido_pelo_cliente_o_pedido_deve_ser_evoluido_para_o_status_FINISHED(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);
        var cookSetStatusUrl = getProperties().baseUrlCook() + COOK_BREADCRUMB_SLASH + orderId + COOK_STATUS_FINISH;
        var orderStatusUrl = baseUrlOrder + ORDER_BREADCRUMB_SLASH + orderId;

        requests.patch(cookSetStatusUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(ORDER_ID, equalTo(orderId))
                .body(STATUS, equalTo(FINISHED));

        await(FIVE_SECONDS);

        requests.get(orderStatusUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(ORDER_ID, equalTo(orderId))
                .body(STATUS, equalTo(FINISHED));
    }
    @Entao("o pedido deve ser evoluido para o status FINISHED")
    public void o_pedido_deve_ser_evoluido_para_o_status_FINISHED(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);
        var orderStatusUrl = baseUrlOrder + ORDER_BREADCRUMB_SLASH + orderId;

        await(FIVE_SECONDS);

        requests.get(orderStatusUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(ORDER_ID, equalTo(orderId))
                .body(STATUS, equalTo(FINISHED));
    }
}
