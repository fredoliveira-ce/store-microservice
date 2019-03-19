package com.fredoliveira.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;

    @NotNull(message = "{NotNull.payment.creditCardNumber}")
    @Column(name = "credit_card_number")
    private Long creditCardNumber;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

}
