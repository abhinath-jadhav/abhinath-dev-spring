package com.akasa.food.order.controller;

import com.akasa.food.order.dto.Response;
import com.akasa.food.order.repository.InventoryRepository;
import com.akasa.food.order.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {


    @Autowired
    private InventoryService inventoryService;


    @GetMapping("/inventory")
    public ResponseEntity<Response> getAllInventory(){
        Response response = inventoryService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<Response> getInventory(@PathVariable Long id){
        Response response = inventoryService.getInventory(id);
        return ResponseEntity.ok(response);
    }
}
