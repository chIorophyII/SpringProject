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

    public Ordering(String restaurantName, List<OrderMenu> foods, Long deliveryFee, Long totalPrice) {
        this.restaurantName = restaurantName;
        this.foods = foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }
}
