package com.sparta.delivery.repository;

import com.sparta.delivery.model.Food;
import com.sparta.delivery.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByRestaurantId(Long restraurantId);
//    Optional<Food> findByName(String name);
    Optional<Food> findByRestaurantIdAndName(Long restaurantId, String name);
}
