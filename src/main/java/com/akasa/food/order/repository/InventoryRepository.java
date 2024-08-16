package com.akasa.food.order.repository;

import com.akasa.food.order.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByItemId(Long id);

    List<Inventory> findAllByItemIdIn(List<Long> list);

}
