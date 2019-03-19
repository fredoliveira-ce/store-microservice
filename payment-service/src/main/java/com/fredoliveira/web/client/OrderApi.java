package com.fredoliveira.web.client;

import com.fredoliveira.domain.model.Payment;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("order-service")
@Headers({"Content-type", "application/json"})
public interface OrderApi {

    @GetMapping("/orders/{id}")
    ResponseEntity<Payment> findById(@PathVariable("id") Long id);

}

