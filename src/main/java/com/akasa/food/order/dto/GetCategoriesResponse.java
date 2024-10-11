package com.akasa.food.order.dto;

import com.akasa.food.order.models.Category;
import com.akasa.food.order.models.FoodItem;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class GetCategoriesResponse extends Response{
    private String message;
    private String status;
    private List<Category> categories;

}
