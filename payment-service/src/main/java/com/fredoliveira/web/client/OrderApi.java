package com.fredoliveira.web.client;

import com.fredoliveira.domain.model.Order;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient("order-service")
@Headers({"Content-type", "application/json"})
public interface OrderApi {

    @GetMapping("/orders/{id}")
    Optional<Order> findById(@PathVariable("id") Long id);

    @PostMapping("/orders")
    Optional<Order> save(@RequestBody Order order);

}

