package com.menu.orderBy.controller;

import com.menu.orderBy.model.Order;
import com.menu.orderBy.model.dto.OrderDto;
import com.menu.orderBy.model.requsetDto.OrderRequestDto;
import com.menu.orderBy.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/request")
    public ResponseEntity<OrderDto> crateOrder(@RequestBody OrderRequestDto orderRequestDto) throws Exception {
        OrderDto order = orderService.crateOrder(orderRequestDto);
        return ResponseEntity.ok().body(order);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> findAllOrder() {
         List<OrderDto> orders = orderService.findAllOrder();
        return ResponseEntity.ok().body(orders);
    }
}
