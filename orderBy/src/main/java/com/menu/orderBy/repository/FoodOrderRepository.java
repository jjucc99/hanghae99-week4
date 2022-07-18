package com.menu.orderBy.repository;

import com.menu.orderBy.model.FoodOrder;
import com.menu.orderBy.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    @Transactional
    void deleteFoodOrderByOrder(Order order);
}
