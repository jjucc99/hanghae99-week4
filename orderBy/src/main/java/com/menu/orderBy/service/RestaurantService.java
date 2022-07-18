package com.menu.orderBy.service;

import com.menu.orderBy.model.dto.RestaurantDto;
import com.menu.orderBy.model.Restaurant;
import com.menu.orderBy.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant registerRestaurant(RestaurantDto restaurantDto) {
        int minOrderPrice = restaurantDto.getMinOrderPrice();
        int deliveryFee = restaurantDto.getDeliveryFee();

        // 최소 주문 가격에 대한 검증로직
        if (minOrderPrice < 1000 || minOrderPrice > 100000) {
            throw new IllegalArgumentException("주문 가격이 기준보다 낮거나 큽니다");
        }
        if (minOrderPrice%100 !=0) {
            throw new IllegalArgumentException("100원 단위로 입력해 주세요");
        }

        //기본 배달비에 대한 검증로직
        if (deliveryFee < 0 || deliveryFee > 10000) {
            throw new IllegalArgumentException("배달비가 기준보다 낮거나 큽니다");
        }
        if (deliveryFee % 500 !=0) {
            throw new IllegalArgumentException("500d원 단위로 입력해 주세요");
        }

        Restaurant restaurant = new Restaurant(restaurantDto);
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
}
