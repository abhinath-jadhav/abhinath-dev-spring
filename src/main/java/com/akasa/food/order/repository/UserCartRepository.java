package com.akasa.food.order.repository;

import com.akasa.food.order.models.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Long> {

    UserCart findByUserId(String user);

    void deleteByUserId(String user);
}
