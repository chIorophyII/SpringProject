package com.sparta.delivery.repository;

import com.sparta.delivery.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Ordering, Long> {
//    Optional<Ordering> findByRestaurantName(String restaurantName);
}
