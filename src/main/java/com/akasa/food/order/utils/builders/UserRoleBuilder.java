package com.akasa.food.order.utils.builders;

import com.akasa.food.order.models.UserRole;

public class UserRoleBuilder {
    private Long id;
    private String name;

    public static UserRoleBuilder builder(){
        return new UserRoleBuilder();
    }

    public UserRoleBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserRole build() {
        UserRole userRole = new UserRole();
        userRole.setName(this.name);
        return userRole;
    }
}