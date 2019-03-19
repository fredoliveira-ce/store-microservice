package com.fredoliveira.domain.model;

public enum OrderStatus {

    PENDING("1", "Pending"),
    AWAITING_PAYMENT("2", "Awaiting PaymentEntity"),
    COMPLETED("3", "Completed"),
    DECLINED("4", "Declined"),
    PARTIALLY_REFUNDED("5", "Partially Refunded"),
    REFUNDED("6", "Refunded");

    private String id;
    private String descricao;

    OrderStatus(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

}
