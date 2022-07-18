package com.menu.orderBy.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FoodOrderDto {
    String name;
    int quantity;
    int price;
}
