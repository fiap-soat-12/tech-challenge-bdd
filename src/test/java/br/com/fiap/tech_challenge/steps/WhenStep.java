package br.com.fiap.tech_challenge.steps;

import br.com.fiap.tech_challenge.common.BaseStep;
import br.com.fiap.tech_challenge.common.ScenarioContext;
import br.com.fiap.tech_challenge.common.ScenarioContextEnum;
import br.com.fiap.tech_challenge.util.Requests;
import io.cucumber.java.pt.Quando;
import org.apache.http.HttpStatus;

import static br.com.fiap.tech_challenge.common.Wait.await;
import static br.com.fiap.tech_challenge.constants.Constants.AssertConstants.FALSE;
import static br.com.fiap.tech_challenge.constants.Constants.AssertConstants.TRUE;
import static br.com.fiap.tech_challenge.constants.Constants.EndpointConstants.ORDER_BREADCRUMB_SLASH;
import static br.com.fiap.tech_challenge.constants.Constants.EndpointConstants.PAID_STATUS_BREADCRUMB;
import static br.com.fiap.tech_challenge.constants.Constants.TimesConstants.FIVE_SECONDS;
import static br.com.fiap.tech_challenge.properties.PropertiesConfigurationManager.getProperties;
import static br.com.fiap.tech_challenge.util.BodyFactory.confirmPayment;
import static br.com.fiap.tech_challenge.util.BodyFactory.refusePayment;
import static org.hamcrest.Matchers.equalTo;

public class WhenStep extends BaseStep {

    private final String baseUrlOrder = getProperties().baseUrlOrder();
    private final Requests requests = new Requests();

    public WhenStep(ScenarioContext context) {
        super(context);
    }

    @Quando("efetua o pagamento do pedido e o pagamento é confirmado")
    public void efetua_o_pagamento_do_pedido_e_o_pagamento_e_confirmado(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);
        var isPaidUrl = baseUrlOrder + ORDER_BREADCRUMB_SLASH + orderId + PAID_STATUS_BREADCRUMB;
        var setPaidUrl = baseUrlOrder + ORDER_BREADCRUMB_SLASH + orderId;

        requests.get(isPaidUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(FALSE));

        requests.put(setPaidUrl, confirmPayment().toString())
                .statusCode(HttpStatus.SC_OK);

        await(FIVE_SECONDS);

        requests.get(isPaidUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(TRUE));
    }

    @Quando("efetua o pagamento do pedido e o pagamento é recusado")
    public void efetua_o_pagamento_do_pedido_e_o_pagamento_e_recusado(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);
        var setNotPaidUrl = baseUrlOrder + ORDER_BREADCRUMB_SLASH + orderId;
        var isPaidUrl = baseUrlOrder + ORDER_BREADCRUMB_SLASH + orderId + PAID_STATUS_BREADCRUMB;

        requests.put(setNotPaidUrl, refusePayment().toString())
                .statusCode(HttpStatus.SC_OK);

        await(FIVE_SECONDS);

        requests.get(isPaidUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(FALSE));
    }
}
