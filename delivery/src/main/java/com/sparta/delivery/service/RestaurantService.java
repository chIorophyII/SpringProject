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

    public Restaurant createRestaurant(RestaurantDto requestDto) throws IllegalAccessException {
        Long minorOrderPrice = requestDto.getMinororderprice();
        Long deliveryFee = requestDto.getDeliveryfee();

        if ((minorOrderPrice < 1000)||(minorOrderPrice > 100000)) {
            throw new IllegalAccessException("허용값을 벗어났습니다.");
        } else if (minorOrderPrice % 100 != 0) {
            throw new IllegalAccessException("100원 단위로 입력해 주세요.");
        }

        if ((deliveryFee < 0)||(deliveryFee > 10000)) {
            throw new IllegalAccessException("허용값을 벗어났습니다.");
        } else if (deliveryFee % 500 != 0) {
            throw  new IllegalAccessException("500원 단위로 입력해 주세요");
        }

        Restaurant restaurant = new Restaurant(requestDto);
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
