package com.akasa.food.order.repository;

import com.akasa.food.order.models.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Long> {

    // SELECT * FROM FOOD_ITEM ORDER BY RAND() LIMIT 10;

    List<FoodItem> findByIsFeaturedTrue();
}
