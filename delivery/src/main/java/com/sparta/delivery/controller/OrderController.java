package com.sparta.delivery.controller;

import com.sparta.delivery.dto.OrderRequestDto;
import com.sparta.delivery.dto.OrderResponseDto;
import com.sparta.delivery.model.Ordering;
import com.sparta.delivery.repository.OrderRepository;
import com.sparta.delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class OrderController{

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping("/order/request")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

//    @GetMapping("/orders")
//    public List<OrderResponseDto> getOrder() {
//        return orderService.getOrder();
//    }
}
