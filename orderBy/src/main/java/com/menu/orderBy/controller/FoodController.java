package com.menu.orderBy.controller;


import com.menu.orderBy.model.dto.FoodDto;
import com.menu.orderBy.model.Food;
import com.menu.orderBy.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public ResponseEntity<Food> foodRegister(@RequestBody List<FoodDto> foodDtoList, @PathVariable("restaurantId") Long restaurantId) {
        foodService.foodRegister(foodDtoList, restaurantId);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public ResponseEntity<List<Food>> findByRestaurantId(@PathVariable("restaurantId") Long restaurantId) {
        List<Food> byRestaurantId = foodService.findByRestaurantId(restaurantId);
        return ResponseEntity.ok().body(byRestaurantId);
    }
}
