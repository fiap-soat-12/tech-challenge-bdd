package br.com.fiap.tech_challenge.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class EndpointConstants {
        public static final String ORDER_BREADCRUMB = "/order/v1/orders";
        public static final String SLASH = "/";
        public static final String ORDER_BREADCRUMB_SLASH = ORDER_BREADCRUMB + SLASH;
        public static final String COOK_BREADCRUMB_SLASH = "/cook/v1/orders" + SLASH;
        public static final String PAID_STATUS_BREADCRUMB = "/paid-status";
        public static final String COOK_STATUS_READY = "/status/" + StatusTypesConstants.READY;
        public static final String COOK_STATUS_FINISH = "/status/" + StatusTypesConstants.FINISHED;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TimesConstants {
        public static final Long FIVE_SECONDS = 5000L;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AssertConstants {
        public static final String FALSE = "false";
        public static final String TRUE = "true";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class BodyAttributesConstants {
        public static final String ORDER_ID = "orderId";
        public static final String CUSTOMER_ID = "customerId";
        public static final String PRODUCTS = "products";
        public static final String IS_PAID = "isPaid";
        public static final String SEQUENCE = "sequence";
        public static final String STATUS = "status";
        public static final String PRODUCT_ID = "id";
        public static final String OBSERVATION = "observation";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class StatusTypesConstants {
        public static final String PREPARING = "PREPARING";
        public static final String READY = "READY";
        public static final String FINISHED = "FINISHED";
    }
}
