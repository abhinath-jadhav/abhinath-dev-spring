package com.akasa.food.order.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String type;
}
