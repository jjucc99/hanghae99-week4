package com.menu.orderBy.model.dto;


import lombok.*;

@Getter
@Setter
public class RestaurantDto {
    public Long id;
    public String name;
    public int minOrderPrice;
    public int deliveryFee;
}
