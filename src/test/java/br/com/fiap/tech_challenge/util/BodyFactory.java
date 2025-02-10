package br.com.fiap.tech_challenge.util;

import org.json.JSONObject;

import java.util.List;

import static br.com.fiap.tech_challenge.constants.Constants.BodyAttributesConstants.*;
import static br.com.fiap.tech_challenge.constants.Constants.BodyAttributesConstants.OBSERVATION;

public class BodyFactory {

    public static JSONObject createOrder(){
        return new JSONObject()
                .put(CUSTOMER_ID, "3d6fa6be-9dfb-4e51-b4d8-08f9351a2f17")
                .put(PRODUCTS,
                        List.of(
                                new JSONObject()
                                        .put(PRODUCT_ID, "bf00ac4a-dd15-4693-8dde-a20e8babf993")
                                        .put(OBSERVATION, "sem queijo"),
                                new JSONObject()
                                        .put(PRODUCT_ID, "bf00ac4a-dd15-4693-8dde-a20e8babf993")
                                        .put(OBSERVATION, "sem queijo"),
                                new JSONObject()
                                        .put(PRODUCT_ID, "bf00ac4a-dd15-4693-8dde-a20e8babf993")
                                        .put(OBSERVATION, "sem queijo"))
                );
    }

    public static JSONObject confirmPayment() {
        return new JSONObject()
                .put(IS_PAID, true);
    }

    public static JSONObject refusePayment() {
        return new JSONObject()
                .put(IS_PAID, false);
    }
}
