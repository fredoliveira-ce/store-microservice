package com.fredoliveira.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "\"order\"")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = OrderEntity.class)
public class OrderEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull(message = "{NotNull.order.address}")
    @Column(name = "address")
    private String address;

    @Column(name = "confirmation_date")
    private LocalDateTime confirmationDate;

    @Column(name = "status")
    private String status;

    @OneToMany(fetch = EAGER, mappedBy = "order", cascade = ALL)
    private Set<OrderItemEntity> items;

    @Column(name = "payment_id")
    private Long paymentId;

}
