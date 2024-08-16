package com.akasa.food.order.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterRequest extends Response{

    private String email;
    private String password;
    private String confirmPassword;

}
