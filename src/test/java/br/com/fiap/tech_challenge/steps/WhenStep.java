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
import static br.com.fiap.tech_challenge.constants.Constants.EndpointConstants.ORDER_BREADCRUMB;
import static br.com.fiap.tech_challenge.constants.Constants.EndpointConstants.PAID_STATUS_BREADCRUMB;
import static br.com.fiap.tech_challenge.constants.Constants.ParamTimes.FIVE_SECONDS;
import static br.com.fiap.tech_challenge.properties.PropertiesConfigurationManager.getProperties;
import static br.com.fiap.tech_challenge.util.BodyFactory.confirmPayment;
import static br.com.fiap.tech_challenge.util.BodyFactory.refusePayment;
import static org.hamcrest.Matchers.equalTo;

public class WhenStep extends BaseStep {

    private final String baseUrl = getProperties().baseUrl();
    private final Requests requests = new Requests();

    public WhenStep(ScenarioContext context) {
        super(context);
    }

    @Quando("o pagamento do pedido é confirmado")
    public void o_pagamento_do_pedido_e_confirmado(){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);
        var isPaidUrl = baseUrl + ORDER_BREADCRUMB + orderId + PAID_STATUS_BREADCRUMB;
        var setPaidUrl = baseUrl + ORDER_BREADCRUMB + orderId;

        requests.get(isPaidUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(FALSE));

        requests.put(setPaidUrl, confirmPayment(orderId).toString())
                .statusCode(HttpStatus.SC_OK);

        await(FIVE_SECONDS);

        requests.get(isPaidUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(TRUE));
    }

    @Quando("o pagamento do pedido é recusado")
    public void o_pagamento_do_pedido_e_recusado (){
        var orderId = context().getByKey(ScenarioContextEnum.ORDER_ID);
        var setNotPaidUrl = baseUrl + ORDER_BREADCRUMB + orderId;
        var isPaidUrl = baseUrl + ORDER_BREADCRUMB + orderId + PAID_STATUS_BREADCRUMB;

        requests.put(setNotPaidUrl, refusePayment(orderId).toString())
                .statusCode(HttpStatus.SC_OK);

        await(FIVE_SECONDS);

        requests.get(isPaidUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(FALSE));
    }
}
