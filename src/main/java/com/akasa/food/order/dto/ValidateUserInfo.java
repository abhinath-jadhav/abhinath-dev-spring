package com.akasa.food.order.dto;

import lombok.Data;

@Data
public class ValidateUserInfo {
    private String username;
    private String password;
    private String confirmPassword;
}
