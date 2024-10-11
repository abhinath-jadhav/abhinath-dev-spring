package com.akasa.food.order.models;

import com.akasa.food.order.models.CartItem;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = "items")
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> items;
    private String orderId;
    private Double payment;
    private String userId;
    private String status;
    private String flight;
    private String paymentMode;

}
