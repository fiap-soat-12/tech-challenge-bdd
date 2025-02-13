package br.com.fiap.tech_challenge.steps;

import br.com.fiap.tech_challenge.common.BaseStep;
import br.com.fiap.tech_challenge.common.ScenarioContext;
import br.com.fiap.tech_challenge.common.ScenarioContextEnum;
import br.com.fiap.tech_challenge.util.Requests;
import io.cucumber.java.pt.Dado;
import org.apache.http.HttpStatus;

import static br.com.fiap.tech_challenge.common.Wait.await;
import static br.com.fiap.tech_challenge.constants.Constants.BodyAttributesConstants.*;
import static br.com.fiap.tech_challenge.constants.Constants.EndpointConstants.ORDER_BREADCRUMB;
import static br.com.fiap.tech_challenge.constants.Constants.EndpointConstants.PAYMENT_BREADCRUMB_SLASH;
import static br.com.fiap.tech_challenge.constants.Constants.TimesConstants.FIVE_SECONDS;
import static br.com.fiap.tech_challenge.properties.PropertiesConfigurationManager.getProperties;
import static br.com.fiap.tech_challenge.util.BodyFactory.createOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GivenStep extends BaseStep {

    private final String baseUrlOrder = getProperties().baseUrlOrder();
    private final String baseUrlPayment = getProperties().baseUrlPayment();
    private final Requests requests = new Requests();

    public GivenStep(ScenarioContext context) {
        super(context);
    }

    @Dado("que um cliente cria um pedido e obtem o qrcode")
    public void que_um_cliente_cria_um_pedido_e_obtem_o_qr_code(){
        var createOrderUrl = baseUrlOrder + ORDER_BREADCRUMB;

        var response = requests.post(createOrderUrl, createOrder().toString());
        var orderId = response.jsonPath().getString(ORDER_ID);

        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body(ORDER_ID, equalTo(orderId))
                .body(SEQUENCE, notNullValue());

        context().add(ScenarioContextEnum.ORDER_ID, orderId);

        await(FIVE_SECONDS);

        var getQrCodePaymentUrl = baseUrlPayment + PAYMENT_BREADCRUMB_SLASH + orderId;

        requests.get(getQrCodePaymentUrl)
                .statusCode(HttpStatus.SC_OK)
                .body(QR_CODE, notNullValue());
    }
}
