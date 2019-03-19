package com.fredoliveira.util;

public enum MessageKey {

    ERROR_VALIDATION_DESCRIPTION("error.validation.description"),

    STORE_NOT_FOUND("store.not.found"),
    STORE_ALREADY_EXISTS("store.already.exists");

    private String key;

    MessageKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return this.key;
    }

}
