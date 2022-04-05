package com.sparta.delivery.repository;

import com.sparta.delivery.model.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu,Long> {
}
