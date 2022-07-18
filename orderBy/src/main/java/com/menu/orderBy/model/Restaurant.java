package com.menu.orderBy.model;

import com.menu.orderBy.model.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int minOrderPrice;

    @Column(nullable = false)
    int deliveryFee;

    public Restaurant(RestaurantDto restaurantDto) {
        this.name = restaurantDto.getName();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
        this.deliveryFee = restaurantDto.getDeliveryFee();
    }
}

