package com.sparta.delivery.repository;

import com.sparta.delivery.dto.OrderRequestDto;
import com.sparta.delivery.dto.OrderResponseDto;
import com.sparta.delivery.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Ordering, Long> {
    Optional<Ordering> findByRestaurantName(String restaurantName);
}
