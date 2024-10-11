package com.akasa.food.order.repository;

import com.akasa.food.order.models.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietRepo extends JpaRepository<Diet, Long> {
}
