package com.akasa.food.order.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class OrderResponse extends Response{

    private String status;
    private String message;
    private String orderId;
}
