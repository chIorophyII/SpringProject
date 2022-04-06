package com.sparta.delivery.repository;

import com.sparta.delivery.model.Food;
import com.sparta.delivery.model.OrderMenu;
import com.sparta.delivery.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMenuRepository extends JpaRepository<OrderMenu,Long> {
//    List<OrderMenu> findAll();
}
