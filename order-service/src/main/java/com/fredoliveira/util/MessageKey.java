package com.fredoliveira.util;

public enum MessageKey {

    ERROR_VALIDATION_DESCRIPTION("error.validation.description"),

    ORDER_NOT_FOUND("order.not.found"),
    ORDER_REFOUND_NOT_ALLOWED("order.refound.not.allowed"),
    ORDER_PAYMENT_NOT_FOUND("order.payment.not.found"),
    ORDER_ALREADY_EXISTS("order.already.exists"),
    ITEM_ORDER_ALREADY_EXISTS("item.order.already.exists");

    private String key;

    MessageKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return this.key;
    }

}
