package com.fredoliveira.web;

import com.fredoliveira.data.entity.OrderEntity;
import com.fredoliveira.data.entity.OrderItemEntity;
import com.fredoliveira.domain.exception.OrderException;
import com.fredoliveira.domain.exception.OrderNotFoundException;
import com.fredoliveira.domain.exception.OrderRefoundException;
import com.fredoliveira.domain.service.OrderItemService;
import com.fredoliveira.domain.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class OrderResource {

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    @Autowired
    public OrderResource(
            OrderService orderService,
            OrderItemService orderItemService
    ) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @ApiOperation("Save a new order.")
    @PostMapping(value = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity save(@Valid @RequestBody OrderEntity order) throws Exception {
        order.getItems().forEach(item -> item.setOrder(order));

       return ofNullable(orderService.save(order))
                .map(currentOrder -> ok(orderService.findOneById(currentOrder.getId())))
                .orElseThrow(Exception::new);
    }

    @ApiOperation("Find an order by id.")
    @GetMapping(produces = APPLICATION_JSON_VALUE, value="/{id}")
    public ResponseEntity findOneById(@PathVariable Long id) throws OrderException {
        return orderService.findOneById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(OrderNotFoundException::new);
    }

    @ApiOperation("Refound an order by id.")
    @PutMapping(produces = APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity refoundOrder(@PathVariable Long id) throws Exception {
        Optional<OrderEntity> order = orderService.findOneById(id);

        if (!order.isPresent()) {
            return notFound().build();
        }

        return ofNullable(orderService.refoundOrder(order.get()))
                .map(updatedOrder -> ok(orderService.findOneById(updatedOrder.getId())))
                .orElseThrow(OrderRefoundException::new);

    }

    @ApiOperation("Find an order item by id.")
    @PutMapping(produces = APPLICATION_JSON_VALUE, value = "/item/{id}")
    public ResponseEntity refoundOrderItem(@PathVariable Long id) throws Exception {
        Optional<OrderItemEntity> orderItem = orderItemService.findOneById(id);

        if (!orderItem.isPresent()) {
            return notFound().build();
        }

        return ofNullable(orderService.refoundOrderItem(orderItem.get()))
                .map(updatedOrderItem -> ok(orderItemService.findOneById(updatedOrderItem.getId())))
                .orElseThrow(OrderRefoundException::new);

    }

}
