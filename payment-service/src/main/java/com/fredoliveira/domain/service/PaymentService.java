package com.fredoliveira.domain.service;

import com.fredoliveira.data.entity.PaymentEntity;
import com.fredoliveira.data.repository.PaymentRepository;
import com.fredoliveira.domain.exception.OrderNotFoundException;
import com.fredoliveira.domain.model.Order;
import com.fredoliveira.web.client.OrderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private OrderApi orderApi;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, OrderApi orderApi) {
        this.paymentRepository = paymentRepository;
        this.orderApi = orderApi;
    }

    public Optional<PaymentEntity> findOneById(Long id) {
        return paymentRepository.findById(id);
    }

    public PaymentEntity save(Long idOrder, PaymentEntity payment) throws OrderNotFoundException {

        Optional<Order> order = orderApi.findById(idOrder);

        if (!order.isPresent()) {
            throw new OrderNotFoundException();
        }

        payment.setPaymentDate(LocalDateTime.now());
        PaymentEntity newPayment = paymentRepository.save(payment);

        updateOrder(order.get(), newPayment);

        return newPayment;
    }

    private void updateOrder(Order order, PaymentEntity newPayment) {
        order.setPaymentId(newPayment.getId());

        orderApi.save(order);
    }

}
