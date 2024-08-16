package com.akasa.food.order.models;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Data
@Document
@ToString
public class UserCart {

    @Id
    private String id;

    private String userId;

    private Set<CartItem> items = new HashSet<>();
}
