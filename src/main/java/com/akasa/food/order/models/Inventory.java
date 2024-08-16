package com.akasa.food.order.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;
    private String itemName;
    private int stock;
}
