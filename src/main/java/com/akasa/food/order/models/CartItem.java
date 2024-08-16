package com.akasa.food.order.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartItem {

    private Long item;
    private Integer quantity;

}