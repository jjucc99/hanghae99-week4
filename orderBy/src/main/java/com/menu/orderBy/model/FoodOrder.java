package com.menu.orderBy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.menu.orderBy.model.dto.FoodOrderDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    int quantity;

    //개수 * 가격
    @Column(nullable = false)
    int price;

    @Column(nullable = false)
    String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference // 순환참조 방지
    @JoinColumn(name = "menu_id")
    Food food;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference // 순환참조 방지
    @JoinColumn(name = "orders_id")
    Order order;

    /**
     *
     * @param foodOrderDto
     *
     * price 는 음식의 값을 설정
     * name 는 음식의 이름을 설정
     */
    public FoodOrder(FoodOrderDto foodOrderDto) {
        this.quantity = foodOrderDto.getQuantity();
        this.price = foodOrderDto.getPrice();
        this.name = foodOrderDto.getName();
    }

    //연관관계 편의 메서드

    //order을 세팅한다
    public void setOrder(Order order) {
        this.order = order;
        order.getFoodOrders().add(this);
    }

    //food를 세팅한다.
    public void setFood(Food food) {
        this.food = food;
        food.getFoodOrders().add(this);
    }
}
