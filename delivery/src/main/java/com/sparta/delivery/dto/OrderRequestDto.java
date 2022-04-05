package com.sparta.delivery.dto;

import com.sparta.delivery.model.OrderMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderRequestDto {
    private Long restaurantId;
    private List<OrderMenu> foods;

}
