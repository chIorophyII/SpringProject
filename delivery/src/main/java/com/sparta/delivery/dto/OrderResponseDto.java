package com.sparta.delivery.dto;

import com.sparta.delivery.model.Ordering;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponseDto {
    private String restaurantName;
    private List<OrderMenuResponseDto> foods;
    private Long deliveryFee;
    private Long totalPrice;

    public OrderResponseDto(String restaurantName, List<OrderMenuResponseDto> orderMenuResponseList, Long deliveryFee, Long totalPrice) {
        this.restaurantName = restaurantName;
        this.foods = orderMenuResponseList;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }
}
