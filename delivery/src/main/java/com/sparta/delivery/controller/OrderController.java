package com.sparta.delivery.controller;

import com.sparta.delivery.dto.OrderRequestDto;
import com.sparta.delivery.dto.OrderResponseDto;
import com.sparta.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController{

    private final OrderService orderService;

    @PostMapping("/order/request")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> getOrder() {
        return orderService.getOrder();
    }
}
