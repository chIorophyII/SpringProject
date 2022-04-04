package com.sparta.delivery.controller;

import com.sparta.delivery.dto.FoodDto;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;

    // 음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood (@RequestBody List<FoodDto> foodList, @PathVariable Long restaurantId) {
        foodService.createFood(foodList, restaurantId);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFood(@PathVariable Long restaurantId) {
        return foodService.getMenu(restaurantId);
    }
}
