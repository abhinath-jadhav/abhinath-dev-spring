package com.akasa.food.order.models.enums;

public enum Role {
    ROLE_ADMIN,
    ROLE_USER;

    public static boolean hasRole(String role) {
        for(Role r : Role.values()){
            if(role.equals(r.name())){
                return true;
            }
        }
        return false;
    }
}