package com.sparta.delivery.service;

import com.sparta.delivery.dto.RestaurantDto;
import com.sparta.delivery.model.Restaurant;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant registerRestaurant(RestaurantDto requestDto){
        Long minOrderPrice = requestDto.getMinOrderPrice();
        Long deliveryFee = requestDto.getDeliveryFee();

        if ((minOrderPrice < 1000)||(minOrderPrice > 100000)) {
            throw new IllegalArgumentException("허용값을 벗어났습니다.");
        }
        if (minOrderPrice % 100 != 0) {
            throw new IllegalArgumentException("100원 단위로 입력해 주세요.");
        }

        if ((deliveryFee < 0)||(deliveryFee > 10000)) {
            throw new IllegalArgumentException("허용값을 벗어났습니다.");
        }
        if (deliveryFee % 500 != 0) {
            throw  new IllegalArgumentException("500원 단위로 입력해 주세요.");
        }

        Restaurant restaurant = new Restaurant(requestDto);
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
