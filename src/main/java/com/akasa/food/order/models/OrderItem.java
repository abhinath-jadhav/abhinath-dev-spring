package com.akasa.food.order.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Data
@ToString(exclude = "order")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long item;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = true)
    @JsonIgnore
    private Order order;

}