package com.akasa.food.order.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Builder
public class ErrorResponse extends Response {
    private String message;
    private String status;
    private String error;
}
