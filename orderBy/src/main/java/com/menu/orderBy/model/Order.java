package com.menu.orderBy.model;


import com.menu.orderBy.model.dto.OrderDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "ORDERS")
public class Order {

    @Id
    @Column(name = "orders_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String restaurantName;


    @Column(nullable = false)
    int deliveryFee;

    int totalPrice;

    @OneToMany(mappedBy = "order")
    List<FoodOrder> foodOrders = new ArrayList<>();

    public Order(OrderDto orderDto) {
        this.restaurantName = orderDto.getRestaurantName();
        this.deliveryFee = orderDto.getDeliveryFee();
        this.totalPrice = orderDto.getTotalPrice();
    }

    public Order(String restaurantName, int deliveryFee) {
        this.restaurantName = restaurantName;
        this.deliveryFee = deliveryFee;
    }

    public void setTotalPrice() {
        this.totalPrice =
                foodOrders.stream().map(FoodOrder::getPrice).reduce(Integer::sum).get()
                + this.deliveryFee;
    }
}
