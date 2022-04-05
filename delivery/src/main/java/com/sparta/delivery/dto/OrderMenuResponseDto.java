package com.sparta.delivery.dto;

import lombok.Getter;

@Getter
public class OrderMenuResponseDto {
    private String name;
    private Long quantity;
    private Long price;

    public OrderMenuResponseDto(String name, Long quantity, long price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
