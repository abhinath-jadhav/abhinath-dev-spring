package com.akasa.food.order.controller;

import com.akasa.food.order.dto.Response;
import com.akasa.food.order.models.CartItem;
import com.akasa.food.order.service.CartService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public ResponseEntity<Response> getCartItems(HttpServletRequest request){
        String user = MDC.get("user");
        if(user == null){
            user = request.getHeader("session-id");
        }
        Response res = cartService.getCartItems(user);

        return ResponseEntity.ok(res);

    }

    @PostMapping("/")
    public ResponseEntity<Response> saveCart(@RequestBody List<CartItem> list,HttpServletRequest request){
        String user = MDC.get("user");
        if(user == null){
            user = request.getHeader("session-id");
        }
        Response res = cartService.addCarts(user, list);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Response> addItem(@PathVariable Long id, HttpServletRequest request){
        String user = MDC.get("user");
        if(user == null){
            user = request.getHeader("session-id");
        }
        Response res = cartService.addToCart(id, user);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> removeItem(@PathVariable Long id, HttpServletRequest request){
        String user = MDC.get("user");
        if(user == null){
            user = request.getHeader("session-id");
        }
        Response res = cartService.removeFromCart(id, user);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/details")
    public ResponseEntity<Response> getUserCartDetails(HttpServletRequest request){
        String user = MDC.get("user");
        if(user == null){
            user = request.getHeader("session-id");
        }
        Response res = cartService.getUserCartDetails(user);
        return ResponseEntity.ok(res);
    }
}
