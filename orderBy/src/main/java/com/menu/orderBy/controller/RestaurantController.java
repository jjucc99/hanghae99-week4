package com.menu.orderBy.controller;

import com.menu.orderBy.model.dto.RestaurantDto;
import com.menu.orderBy.model.Restaurant;
import com.menu.orderBy.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantService.registerRestaurant(restaurantDto);
        return ResponseEntity.ok().body(restaurant);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> findAll() {
        List<Restaurant> all = restaurantService.findAll();
        return ResponseEntity.ok().body(all);
    }
}
