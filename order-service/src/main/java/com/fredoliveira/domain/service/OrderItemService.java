package com.fredoliveira.domain.service;

import com.fredoliveira.data.entity.OrderItemEntity;
import com.fredoliveira.data.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public Optional<OrderItemEntity> findOneById(Long id) {
        return orderItemRepository.findById(id);
    }

}
