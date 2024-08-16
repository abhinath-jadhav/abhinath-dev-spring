package com.akasa.food.order.models;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;


@Data
@ToString
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightId;
    private String userId;
    private String path;

}
