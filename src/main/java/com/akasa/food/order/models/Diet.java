package com.akasa.food.order.models;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Diets")
@ToString
public class Diet {

    private String id;
    private String type;
}
