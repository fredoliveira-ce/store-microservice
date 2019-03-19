package com.fredoliveira.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "store")
public class StoreEntity {

    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull(message = "{NotNull.store.name}")
    @Column(name = "name")
    private String name;

    @NotNull(message = "{NotNull.store.address}")
    @Column(name = "address")
    private String address;

}
