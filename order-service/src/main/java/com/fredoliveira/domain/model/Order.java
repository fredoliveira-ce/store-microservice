package com.fredoliveira.domain.model;

import com.fredoliveira.data.entity.OrderItemEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class Order {

    private Long id;
    private String address;
    private LocalDateTime confirmationDate;
    private String status;
    private Set<OrderItemEntity> items;
    private Long paymentId;

}
