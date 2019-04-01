package com.fredoliveira.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class Order {

    private Long id;
    private String address;
    private LocalDateTime confirmationDate;
    private String status;
    private Set<OrderItem> items;
    private Long paymentId;

}
