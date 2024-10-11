package com.akasa.food.order.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Data
@ToString(exclude = "userCart")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long item;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = true)
    private UserCart userCart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(item, cartItem.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }
}