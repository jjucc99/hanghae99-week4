package com.menu.orderBy.repository;

import com.menu.orderBy.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
}
