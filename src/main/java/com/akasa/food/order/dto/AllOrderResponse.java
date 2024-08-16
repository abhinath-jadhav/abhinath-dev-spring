package com.akasa.food.order.dto;

import com.akasa.food.order.models.Order;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class AllOrderResponse extends Response{

    private String status;
    private String message;
    private List<Order> orders;
}
