package com.sparta.delivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Ordering {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderMenu> foods;

    @Column
    private Long deliveryFee;

    @Column
    private Long totalPrice;

    public Ordering(String restaurantName, List<OrderMenu> foods, Long totalPrice, Long deliveryFee) {
        this.restaurantName = restaurantName;
        this.foods = foods;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
    }
}
