package com.sparta.delivery.model;

import com.sparta.delivery.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long minorderprice;

    @Column(nullable = false)
    private Long deliveryfee;

    public Restaurant(RestaurantDto requestDto) {
        this.name = requestDto.getName();
        this.minorderprice = requestDto.getMinororderprice();
        this.deliveryfee = requestDto.getDeliveryfee();
    }

}
