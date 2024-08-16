package com.akasa.food.order.models;

import com.akasa.food.order.models.CartItem;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@ToString
@Document
public class Order {

    @Id
    private String id;
    private List<CartItem> items;
    private Double payment;
    private String orderId;
    private String userId;
    private String status;

}
