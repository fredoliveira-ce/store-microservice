package com.fredoliveira.web.client;

import com.fredoliveira.domain.model.Payment;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("payment-service")
@Headers({"Content-type", "application/json"})
public interface PaymentApi {

    @GetMapping("/payments/{id}")
    ResponseEntity<Payment> findById(@PathVariable("id") Long id);

}

