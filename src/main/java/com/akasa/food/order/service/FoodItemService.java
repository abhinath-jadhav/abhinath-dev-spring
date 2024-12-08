package com.akasa.food.order.service;

import com.akasa.food.order.dto.*;
import com.akasa.food.order.models.CartItem;
import com.akasa.food.order.models.Category;
import com.akasa.food.order.models.FoodItem;
import com.akasa.food.order.models.Inventory;
import com.akasa.food.order.repository.FoodItemRepo;
import com.akasa.food.order.repository.InventoryRepository;
import com.akasa.food.order.repository.ItemCategoryRepo;
import com.akasa.food.order.utils.BeanMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FoodItemService {

    @Autowired
    private FoodItemRepo foodItemRepo;

    @Autowired
    private ItemCategoryRepo itemCategoryRepo;

    @Autowired
    private InventoryService inventoryService;

    public Response getAllItems() {

        log.info("Request received for get all food items.{}", MDC.get("user"));
        List<FoodItem> foodItems = foodItemRepo.findAll();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String s = objectMapper.writeValueAsString(foodItems);



            System.out.println(s);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if(foodItems.isEmpty()){
            log.info("Items not found in the DB");
            return ErrorResponse.builder()
                    .status("500")
                    .error("Empty data")
                    .message("Please try after some time")
                    .build();
        }
        log.info("All items :: {}", foodItems);
        return GetFoodItemsResponse.builder()
                .status("200")
                .message("Success")
                .items(foodItems)
                .build();

    }

    public Response getFavorites() {
        log.info("Get request for fetch favorites");
        List<FoodItem> foodItems = foodItemRepo.findByIsFeaturedTrue();
        if(foodItems.isEmpty()){
            log.info("Items not found in the DB");
            return ErrorResponse.builder()
                    .status("500")
                    .error("Empty data")
                    .message("Please try after some time")
                    .build();
        }
        log.info("Favorites items :: {}", foodItems);
        return GetFoodItemsResponse.builder()
                .status("200")
                .message("Success")
                .items(foodItems)
                .build();
    }

    public Response getCategories() {
        log.info("Get request for fetch categories");
        List<Category> categoryList = itemCategoryRepo.findAll();
        if(categoryList.isEmpty()){
            log.info("Categories not found in the DB");
            return ErrorResponse.builder()
                    .status("500")
                    .error("Empty data")
                    .message("Please try after some time")
                    .build();
        }
        log.info("categories list :: {}", categoryList);
        return GetCategoriesResponse.builder()
                .status("200")
                .message("Success")
                .categories(categoryList)
                .build();
    }

    public Response selectedItems(List<CartItem> list) {
        log.info("Request received for get selected food items by :: {}", MDC.get("user"));

        Map<Long, CartItem> map = list.stream().collect(Collectors.toMap(CartItem::getItem, Function.identity()));

        List<FoodItem> foodItems = foodItemRepo.findAllById(map.keySet());

        if(foodItems.isEmpty()){
            log.info("Items not found in the DB");
            return ErrorResponse.builder()
                    .status("500")
                    .error("Empty data")
                    .message("Please try after some time")
                    .build();
        }

        List<CartDetailsResponse> collect = foodItems.stream()
                .map(o -> BeanMapper.map(o, map.get(o.getId()).getQuantity()))
                .collect(Collectors.toList());
        log.info("Selected items :: {}", collect);

        return GetCartDetailsResponse.builder()
                .status("200")
                .message("Success")
                .cart(collect)
                .build();
    }

    public Response addFood(FoodItem foodItem) {

        if (foodItem.getId() != null) {
            Optional<FoodItem> byId = foodItemRepo.findById(foodItem.getId());
            byId.ifPresentOrElse(
                    (o) -> {
                        foodItemRepo.save(foodItem);
                        log.info("Food Item updated successfully. ");
                    }, () -> {

                        foodItem.setId(null);
                        FoodItem save = foodItemRepo.save(foodItem);
                        Inventory inventory = new Inventory();
                        inventory.setItemName(foodItem.getName());
                        inventory.setStock(0);
                        inventory.setItemId(save.getId());
                        inventoryService.updateInventory(inventory);

                        log.info("Food Item Added successfully. ");
                    }
            );
            return SuccessResponse.builder()
                    .status("200")
                    .message("Item updated successfully.")
                    .build();
        }

        FoodItem save = foodItemRepo.save(foodItem);
        Inventory inventory = new Inventory();
        inventory.setItemName(foodItem.getName());
        inventory.setStock(0);
        inventory.setItemId(save.getId());
        inventoryService.updateInventory(inventory);
        log.info("Food Item updated successfully.");

        return SuccessResponse.builder()
                .status("200")
                .message("Item added successfully.")
                .build();

    }
}
