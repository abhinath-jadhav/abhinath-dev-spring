package com.akasa.food.order.repository;

import com.akasa.food.order.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    Order findByOrderId(String id);

    List<Order> findAllByUserId(String user);
}
