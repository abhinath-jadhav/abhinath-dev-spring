package com.akasa.food.order.repository;

import com.akasa.food.order.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepo extends JpaRepository<Category, Long> {
}
