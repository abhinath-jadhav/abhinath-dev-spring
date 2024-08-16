package com.akasa.food.order.dto;

import com.akasa.food.order.models.Flight;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@Builder
public class GetFlightsResponse extends Response{

    private String status;
    private String message;
    private List<Flight> flights;

}
