package com.akasa.food.order.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCartDto extends Response {
    private Long item;
    private Integer quantity;
    private int status;
}
