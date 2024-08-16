package com.akasa.food.order.dto;

import com.akasa.food.order.models.CartItem;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CartItemsRequest {

    private String status;
    private List<CartItem> list;
    private int count;


}
