package com.menu.orderBy.repository;

import com.menu.orderBy.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Transactional
    void deleteRestaurantByName(String name);
}
