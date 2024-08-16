package com.akasa.food.order.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartDto {

    private Long item;
    private Integer quantity;

}
