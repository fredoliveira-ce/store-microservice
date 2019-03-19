package com.fredoliveira.util;

public enum MessageKey {

    ERROR_VALIDATION_DESCRIPTION("error.validation.description"),

    PAYMENT_NOT_FOUND("payment.not.found"),
    PAYMENT_ALREADY_EXISTS("payment.already.exists"),
    ORDER_NOT_FOUND("order.not.found");

    private String key;

    MessageKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return this.key;
    }

}
