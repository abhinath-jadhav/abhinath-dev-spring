package com.akasa.food.order.repository;

import com.akasa.food.order.models.Diet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietRepo extends MongoRepository<Diet, String> {
}
