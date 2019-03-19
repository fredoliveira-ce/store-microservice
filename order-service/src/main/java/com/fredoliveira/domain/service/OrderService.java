package com.fredoliveira.domain.service;

import com.fredoliveira.data.entity.OrderEntity;
import com.fredoliveira.data.entity.OrderItemEntity;
import com.fredoliveira.data.repository.OrderItemRepository;
import com.fredoliveira.data.repository.OrderRepository;
import com.fredoliveira.domain.exception.OrderPaymentNotFoundException;
import com.fredoliveira.domain.model.OrderStatus;
import com.fredoliveira.domain.model.Payment;
import com.fredoliveira.domain.model.PaymentStatus;
import com.fredoliveira.web.client.PaymentApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentApi paymentApi;

    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            PaymentApi paymentApi) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymentApi = paymentApi;
    }

    public Optional<OrderEntity> findOneById(Long id) {
        return orderRepository.findById(id);
    }

    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

    public OrderEntity refoundOrder(OrderEntity order) throws OrderPaymentNotFoundException {
        if (isNull(order.getPaymentId())) {
            throw new OrderPaymentNotFoundException();
        }

        if (isRefoudAllowed(order)) {
            order.setStatus(OrderStatus.REFUNDED.getId());

            order.getItems().forEach(orderItem -> orderItem.setStatus(OrderStatus.REFUNDED.getId()));

            return orderRepository.save(order);
        }

        return null;
    }

    public OrderItemEntity refoundOrderItem(OrderItemEntity orderItemEntity) {
        if (isRefoudAllowed(orderItemEntity.getOrder())) {
            orderItemEntity.setStatus(OrderStatus.REFUNDED.getId());
            orderItemEntity.getOrder().setStatus(OrderStatus.PARTIALLY_REFUNDED.getId());

            return orderItemRepository.save(orderItemEntity);
        }

        return null;
    }

    private boolean isRefoudAllowed(OrderEntity order) {
        LocalDateTime limitDate = order.getConfirmationDate().plusDays(10);

        Boolean isTenDaysAfterConfirmation = LocalDateTime.now().isAfter(limitDate);

        Payment payment = paymentApi.findById(order.getPaymentId()).getBody();

        Boolean isPaymentConcluded = payment.getStatus().equals(PaymentStatus.CONCLUDED.getId());

        return isTenDaysAfterConfirmation && isPaymentConcluded;
    }
}
