package com.menu.orderBy.model.dto;

import com.menu.orderBy.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {
    private String restaurantName;
    private List<FoodOrderDto> foods;
    private int deliveryFee;
    private int totalPrice;
}
