package com.fredoliveira.web;

import com.fredoliveira.data.entity.PaymentEntity;
import com.fredoliveira.domain.exception.PaymentException;
import com.fredoliveira.domain.exception.PaymentNotFoundException;
import com.fredoliveira.domain.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Optional.ofNullable;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class PaymentResource {

    private final PaymentService paymentService;

    @Autowired public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @ApiOperation("Save a new payment for a specific order.")
    @PostMapping(value = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestParam("idOrder") Long idOrder,@Valid @RequestBody PaymentEntity payment) throws Exception {
        return ofNullable(paymentService.save(idOrder, payment))
                .map(currentPayment -> ok(paymentService.findOneById(currentPayment.getId())))
                .orElseThrow(PaymentNotFoundException::new);
    }

    @ApiOperation("Find an payment by id.")
    @GetMapping(produces = APPLICATION_JSON_VALUE, value="/{id}")
    public ResponseEntity findOneById(@PathVariable Long id) throws PaymentException {
        return paymentService.findOneById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(PaymentNotFoundException::new);
    }

}
