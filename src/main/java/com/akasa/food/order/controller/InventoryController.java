package com.akasa.food.order.controller;

import com.akasa.food.order.dto.Response;
import com.akasa.food.order.models.Inventory;
import com.akasa.food.order.repository.InventoryRepository;
import com.akasa.food.order.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/inventory")
    public ResponseEntity<Response> getInventory(@RequestBody Inventory inventory){
        Response response = inventoryService.updateInventory(inventory);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/inventory")
    public ResponseEntity<Response> add(@RequestBody Inventory inventory){
        Response response = inventoryService.addInventory(inventory);
        return ResponseEntity.ok(response);
    }
}
