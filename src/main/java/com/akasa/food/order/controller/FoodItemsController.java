package com.akasa.food.order.controller;

import com.akasa.food.order.dto.Response;
import com.akasa.food.order.models.CartItem;
import com.akasa.food.order.service.FoodItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/food")
public class FoodItemsController {

    @Autowired
    private FoodItemService foodItemService;

    @Operation(summary = "Protected Resource",
            description = "This resource requires a valid JWT token.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful response", content = @Content(schema = @Schema(implementation = String.class)))
            },
            security = @SecurityRequirement(name = "bearer-key"))
    @GetMapping("/all")
    public ResponseEntity<Response> getAllFoodItems(){
        Response response = foodItemService.getAllItems();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/ids")
    public ResponseEntity<Response> getAllFoodItems(@RequestBody Set<CartItem> list){
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
