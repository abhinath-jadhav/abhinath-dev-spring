package com.akasa.food.order.controller;

import com.akasa.food.order.dto.CartDto;
import com.akasa.food.order.dto.Response;
import com.akasa.food.order.models.CartItem;
import com.akasa.food.order.models.Order;
import com.akasa.food.order.service.UserService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/flight")
    public ResponseEntity<Response> getFlights(){
        String user = MDC.get("user");
        Response res = userService.getFlights(user);

        return ResponseEntity.ok(res);

    }

    @GetMapping("/carts")
    public ResponseEntity<Response> getCartItems(){
        String user = MDC.get("user");
        Response res = userService.getCartItems(user);

        return ResponseEntity.ok(res);

    }

    @PostMapping("/carts")
    public ResponseEntity<?> saveCart(@RequestBody Set<CartItem> list){
        String user = MDC.get("user");
        Response res = userService.addCarts(user, list);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/carts/details")
    public ResponseEntity<?> getUserCartDetails(){
            String user = MDC.get("user");
            Response res = userService.getUserCartDetails(user);
            return ResponseEntity.ok(res);
    }



    @PostMapping("/order")
    public ResponseEntity<?> completeOrder(@RequestBody Order order){
        String user = MDC.get("user");
        Response res = userService.completeOrder(user, order);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders(){
        String user = MDC.get("user");
        Response res = userService.getAllOrders(user);
        return ResponseEntity.ok(res);
    }
}
