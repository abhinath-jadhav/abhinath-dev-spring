package com.akasa.food.order.controller;

import com.akasa.food.order.dto.Response;
import com.akasa.food.order.models.CartItem;
import com.akasa.food.order.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
public class FoodItemsController {

    @Autowired
    private FoodItemService foodItemService;
    @GetMapping("/all")
    public ResponseEntity<Response> getAllFoodItems(){
        Response response = foodItemService.getAllItems();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/ids")
    public ResponseEntity<Response> getAllFoodItems(@RequestBody List<CartItem> list){
        Response response = foodItemService.selectedItems(list);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/favorites")
    public ResponseEntity<Response> getFavorites(){
        Response response = foodItemService.getFavorites();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/categories")
    public ResponseEntity<Response> getCategories(){
        Response response = foodItemService.getCategories();

        return ResponseEntity.ok(response);
    }
}
