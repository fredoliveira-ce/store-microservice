package com.fredoliveira.domain.service;

import com.fredoliveira.data.entity.OrderEntity;
import com.fredoliveira.data.entity.PaymentEntity;
import com.fredoliveira.domain.exception.OrderNotFoundException;
import com.fredoliveira.data.repository.OrderRepository;
import com.fredoliveira.data.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Optional<PaymentEntity> findOneById(Long id) {
        return paymentRepository.findById(id);
    }

    public PaymentEntity save(Long idOrder, PaymentEntity payment) throws OrderNotFoundException {
        Optional<OrderEntity> order = orderRepository.findById(idOrder);

        if (!order.isPresent()) {
            throw new OrderNotFoundException();
        }

        payment.setPaymentDate(LocalDateTime.now());
        PaymentEntity newPayment = paymentRepository.save(payment);

        updateOrder(order, newPayment);

        return newPayment;
    }

    private void updateOrder(Optional<OrderEntity> order, PaymentEntity newPayment) {
        OrderEntity currentOrder = order.get();
        currentOrder.setPaymentId(newPayment.getId());

        orderRepository.save(currentOrder);
    }

}
