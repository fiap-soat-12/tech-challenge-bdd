package br.com.fiap.tech_challenge.constants;

public class Constants {

    private Constants() {}

    public static class EndpointConstants {
        private EndpointConstants() {}
        public static final String ORDER_BREADCRUMB = "/order/v1/orders";
        public static final String SLASH = "/";
        public static final String ORDER_BREADCRUMB_SLASH = ORDER_BREADCRUMB + SLASH;
        public static final String COOK_BREADCRUMB_SLASH = "/api/v1/orders" + SLASH;
        public static final String PAYMENT_BREADCRUMB_SLASH = "/payment/v1/qrs" + SLASH;
        public static final String PAID_STATUS_BREADCRUMB = "/paid-status";
        public static final String COOK_STATUS_READY = "/status/" + StatusTypesConstants.READY;
        public static final String COOK_STATUS_FINISH = "/status/" + StatusTypesConstants.FINISHED;
    }

    public static class TimesConstants {
        private TimesConstants() {}
        public static final Long FIVE_SECONDS = 5000L;
    }

    public static class AssertConstants {
        private AssertConstants() {}
        public static final String FALSE = "false";
        public static final String TRUE = "true";
    }

    public static class BodyAttributesConstants {
        private BodyAttributesConstants() {}
        public static final String ORDER_ID = "orderId";
        public static final String CUSTOMER_ID = "customerId";
        public static final String PRODUCTS = "products";
        public static final String IS_PAID = "isPaid";
        public static final String SEQUENCE = "sequence";
        public static final String STATUS = "status";
        public static final String PRODUCT_ID = "id";
        public static final String OBSERVATION = "observation";
        public static final String QR_CODE = "qr";
    }

    public static class StatusTypesConstants {
        private StatusTypesConstants() {}
        public static final String PREPARING = "PREPARING";
        public static final String READY = "READY";
        public static final String FINISHED = "FINISHED";
    }
}
