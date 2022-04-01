package com.sparta.delivery.controller;

import com.sparta.delivery.dto.RestaurantDto;
import com.sparta.delivery.model.Restaurant;
import com.sparta.delivery.repository.RestaurantRepository;
import com.sparta.delivery.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    // 음식점 조회
    @GetMapping("/api/restaurant")
    public List<Restaurant> getRestaurant() {
        return restaurantRepository.findAll();
    }

    // 음식점 등록
    @PostMapping("/api/restaurant/register")
    public Restaurant createRestaurant(@RequestBody RestaurantDto requestDto) throws IllegalAccessException {
        return restaurantService.createRestaurant(requestDto);
    }
}
