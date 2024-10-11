package com.akasa.food.order.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@ToString(exclude = "items")
public class UserCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userCart")
    private List<CartItem> items = new LinkedList<>();
}
