package com.sparta.delivery.controller;

import com.sparta.delivery.dto.FoodDto;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.model.Restaurant;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;
    private final FoodRepository foodRepository;

    // 음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood (@RequestBody List<FoodDto> foodList, @PathVariable Long restaurantId) {
        foodService.createFood(foodList, restaurantId);
    }
//
//    @GetMapping("/")
//    public List<Food> getFood(@PathVariable Long restaurantId) {
//        return foodRepository.findAllByRestaurantId(restaurantId);
//    }
}
