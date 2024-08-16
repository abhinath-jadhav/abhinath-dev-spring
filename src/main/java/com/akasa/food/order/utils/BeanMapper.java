package com.akasa.food.order.utils;

import com.akasa.food.order.dto.CartDetailsResponse;
import com.akasa.food.order.models.FoodItem;
import org.springframework.beans.BeanUtils;

public class BeanMapper {
    public static CartDetailsResponse map(FoodItem foodItem, Integer qty){
        CartDetailsResponse cartDetailsResponse = new CartDetailsResponse();
        BeanUtils.copyProperties(foodItem, cartDetailsResponse);
        cartDetailsResponse.setQty(qty);
        return cartDetailsResponse;
    }
}
