package com.akasa.food.order.repository;

import com.akasa.food.order.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Long> {
    List<Flight> findByUserId(String user);

}
