package com.menu.orderBy.repository;


import com.menu.orderBy.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findFoodByNameAndAndRestaurantId(String name, Long restaurantId);
    Optional<Food> findFoodByName(String name);
    Optional<Food> findFoodsByRestaurantIdAndId(Long restaurantId, Long foodId);
    List<Food> findFoodByRestaurantId(Long restaurantId);
}
