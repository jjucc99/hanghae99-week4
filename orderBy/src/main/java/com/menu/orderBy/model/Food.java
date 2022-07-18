package com.menu.orderBy.model;

import com.menu.orderBy.model.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long restaurantId;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int price;


    // 주문 생성 시에만 사용
    @Column(nullable = true)
    @OneToMany(mappedBy = "food")
    List<FoodOrder> foodOrders = new ArrayList<>();

    //주문 생성시에만 사용
    @Column
    Long quantity;

    //주믄을 받을 때 이 메소드로 수량을 설정
    private void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Food(FoodDto foodDto, Long restaurantId) {
        this.restaurantId = restaurantId;
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
    }
}
