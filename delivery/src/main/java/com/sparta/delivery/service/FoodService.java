package com.sparta.delivery.service;

import com.sparta.delivery.dto.FoodDto;
import com.sparta.delivery.dto.RestaurantDto;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.model.Restaurant;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service // bean으로 등록하기 위해
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void createFood(List<FoodDto> foodList, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("해당 음식점이 존재하지 않습니다."));

        HashSet<String> foodSet = new HashSet<>();
        for (FoodDto newFood : foodList) {
            foodSet.add(newFood.getName());
        }
        if (foodSet.size() != foodList.size()) {
            throw new IllegalArgumentException("중복된 메뉴가 존재합니다.");
        }


        for (FoodDto newFood : foodList) {
            Food food = new Food(newFood, restaurant);

            Optional<Food> menu = foodRepository.findByRestaurantIdAndName(restaurantId, newFood.getName());
            if (menu.isPresent()) {
                throw new IllegalArgumentException("이미 존재하는 메뉴입니다.");
            }
            if (newFood.getPrice() < 100 || newFood.getPrice() > 100000) {
                throw new IllegalArgumentException("허용값을 벗어났습니다.");
            }
            if (newFood.getPrice() % 100 != 0) {
                throw new IllegalArgumentException("100원 단위로 입력해 주세요.");
            }

            foodRepository.save(food);
        }
    }

    public List<Food> getMenu(Long restaurantId) {
        restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("해당 음식점이 존재하지 않습니다."));
        return foodRepository.findByRestaurantId(restaurantId);
    }

//        public boolean validCheck(List<FoodDto> menu, FoodPlace foodPlace) {
//            for (FoodDto newFood : menu) {
//                if (foodRepository.existsByNameAndFoodPlace(newFood.getName(), foodPlace))
//                    return true;
//            }
//            return false;
//        }
//        if (
//              for (FoodDto newFood : menu) {
////                if (foodRepository.existsByNameAndFoodPlace(newFood.getName(), foodPlace))
////                    return true;
////            }
////            return false;)
//            throw new IllegalArgumentException("이미 포함 됨");

//        for (FoodDto newfood : foodDtoList) {
//            Food food = new Food(newfood, foodPlace);
//            AllExceptions.foodException(food);
//            foodRepository.save(food);
//        }

//        public static void foodPlaceExceptions(RestaurantDto restaurantDto) {
//            if(restaurantDto.getMinOrderPrice() < MINIMUM_ORDER_PRICE || restaurantDto.getMinOrderPrice() > MAXIMUM_ORDER_PRICE)
//                throw new IllegalArgumentException("최저 주문 금액 미충족");
//            if(restaurantDto.getMinOrderPrice() % ORDER_PRICE_UNIT != 0)
//                throw new IllegalArgumentException("최저 주문 금액 단위 위반");
//            if(restaurantDto.getDeliveryFee() % DELIVERY_FEE_UNIT != 0)
//                throw new IllegalArgumentException("배달비 단위 위반");
//            if(restaurantDto.getDeliveryFee() < MINIMUM_DELIVERY_FEE || restaurantDto.getDeliveryFee() > MAXIMUM_DELIVERY_FEE)
//                throw new IllegalArgumentException("배달비 범위 위반");
//        }
//
//        public static void foodException(Food food) {
//            if (food.getPrice() < MINIMUM_FOOD_PRICE || food.getPrice() > MAXIMUM_FOOD_PRICE)
//                throw new IllegalArgumentException("가격 설정 범위 위반");
//            if (food.getPrice() % FOOD_PRICE_UNIT != 0)
//                throw new IllegalArgumentException("가격 설정 단위 위반");
//        }
//
//        public static void orderException(long totalPrice, long deliveryFee, long minOrderPrice) {
//            if(totalPrice - deliveryFee < minOrderPrice)
//                throw new IllegalArgumentException("최소 주문 금액 미충족");
//        }


}
