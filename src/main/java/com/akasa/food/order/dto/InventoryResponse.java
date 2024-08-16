package com.akasa.food.order.dto;

import com.akasa.food.order.models.Inventory;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class InventoryResponse extends Response{
    private String status;
    private String message;
    private Inventory item;
}

