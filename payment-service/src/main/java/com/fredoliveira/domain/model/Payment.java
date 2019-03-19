package com.fredoliveira.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data public class Payment {

    private Long id;
    private String status;
    private Long creditCardNumber;
    private LocalDateTime paymentDate;

}
