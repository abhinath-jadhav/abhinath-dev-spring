package com.akasa.food.order.controller;

import com.akasa.food.order.dto.Response;
import com.akasa.food.order.models.FoodItem;
import com.akasa.food.order.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping("/food")
    public ResponseEntity<Response> updateFood(@RequestBody FoodItem foodItem){
        Response response = foodItemService.addFood(foodItem);

        return ResponseEntity.ok(response);
    }
}
