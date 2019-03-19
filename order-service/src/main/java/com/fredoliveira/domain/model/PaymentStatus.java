package com.fredoliveira.domain.model;

public enum PaymentStatus {

    PENDING("1", "Pending"),
    DECLINED("3", "Declined"),
    CONCLUDED("3", "Concluded");

    private String id;
    private String descricao;

    PaymentStatus(String id, String descricao) {
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
