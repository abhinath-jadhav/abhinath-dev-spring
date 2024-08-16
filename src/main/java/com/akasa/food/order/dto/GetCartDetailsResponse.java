package com.akasa.food.order.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class GetCartDetailsResponse extends Response{
    private String status;
    private String message;
    private List<CartDetailsResponse> items;
}
