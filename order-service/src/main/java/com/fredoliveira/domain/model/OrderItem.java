package com.fredoliveira.domain.model;

import lombok.Data;

@Data public class OrderItem {

    private Long id;
    private String description;
    private Double unitPrice;
    private Double quantity;
    private String status;
    private Long orderId;

}
