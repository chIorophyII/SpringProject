package com.sparta.delivery.model;

import com.sparta.delivery.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Food {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;
//    @Column(nullable = false)
//    private String restaurantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    public Food(FoodDto foodDto, Restaurant restaurant) {
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
        this.restaurant = restaurant;
    }
}