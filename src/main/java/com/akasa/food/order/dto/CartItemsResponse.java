package com.akasa.food.order.dto;

import com.akasa.food.order.models.CartItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class CartItemsResponse extends Response{
    private String status;
    private List<CartItem> list;
    private int count;
}
