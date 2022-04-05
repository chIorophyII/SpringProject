package com.sparta.delivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderMenu {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "FOOD_ID")
    private Food food;

    @Column
    private Long quantity;


    public OrderMenu(Food food, Long quantity) {
        this.food = food;
        this.quantity = quantity;
    }
}
