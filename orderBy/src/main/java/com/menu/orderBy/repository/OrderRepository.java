package com.menu.orderBy.repository;

import com.menu.orderBy.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
