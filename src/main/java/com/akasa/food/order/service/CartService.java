package com.akasa.food.order.service;

import com.akasa.food.order.dto.*;
import com.akasa.food.order.models.CartItem;
import com.akasa.food.order.models.Inventory;
import com.akasa.food.order.models.User;
import com.akasa.food.order.models.UserCart;
import com.akasa.food.order.repository.InventoryRepository;
import com.akasa.food.order.repository.UserCartRepository;
import com.akasa.food.order.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartService {

    @Autowired
    private UserCartRepository cartRepository;

    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private UserRepo userRepo;


    public Response getCartItems(String user) {
        log.info("Get Cart list request for user :: {}", user);

        UserCart cart =  cartRepository.findByUserId(user);

        if(cart == null || cart.getItems() == null || cart.getItems().isEmpty()){
            log.info("Categories not found in the DB");
            return ErrorResponse.builder()
                    .status("500")
                    .error("Empty data")
                    .message("Please try after some time")
                    .build();
        }
        log.info("Flights list :: {}", cart.getItems());
        return CartItemsResponse.builder()
                .status("200")
                .count(cart.getItems().size())
                .list(cart.getItems())
                .build();
    }

    public Response addCarts(String user, List<CartItem> items) {
        Optional<User> byEmail = userRepo.findByEmail(user);

        if(byEmail.isPresent()){
            UserCart userCart = cartRepository.findByUserId(user);
            if(userCart != null){
                userCart.setItems(items);
                cartRepository.save(userCart);
                return foodItemService.selectedItems(items);

            } else {
                UserCart cart = new UserCart();
                cart.setUserId(user);
                cart.setItems(items);
                cartRepository.save(cart);
                return foodItemService.selectedItems(items);
            }

        }
        return ErrorResponse.builder()
                .status("500")
                .error("User not found")
                .message("Enter valid user")
                .build();


    }

    public Response getUserCartDetails(String user) {

        UserCart cart =  cartRepository.findByUserId(user);

        if(cart == null || cart.getItems() == null || cart.getItems().isEmpty()){
            log.info("Cart not found in the DB");
            return ErrorResponse.builder()
                    .status("400")
                    .error("Empty data")
                    .message("Please try after some time")
                    .build();
        }

        Response response = foodItemService.selectedItems(cart.getItems());

        log.info("User Cart :: {}", response);
        return response;

    }

    @Transactional
    public Response addToCart(Long id, String user) {

        Inventory inventory = inventoryRepository.findByItemId(id);
        if(inventory.getStock() == 0 || user == null){
            return UpdateCartDto.builder()
                    .status(400)
                    .build();
        }
        UserCart userCart = cartRepository.findByUserId(user);
        if(userCart != null ){
            List<CartItem> items = userCart.getItems();
            CartItem cartItem = new CartItem();
            cartItem.setItem(id);
            if(items.contains(cartItem)){
                List<CartItem> updatedCart = items.stream().peek(it -> {
                    if (it.getItem().equals(id)) {
                        it.setQuantity(it.getQuantity() + 1);
                        cartItem.setQuantity(it.getQuantity());
                    }
                }).collect(Collectors.toList());
                if(inventory.getStock() < cartItem.getQuantity()){
                    return UpdateCartDto.builder()
                            .status(400)
                            .build();
                } else {
                    userCart.setItems(updatedCart);
                    cartRepository.save(userCart);
                }
            } else {
                cartItem.setQuantity(1);
                cartItem.setUserCart(userCart);
                items.add(cartItem);
                userCart.setItems(items);
                cartRepository.save(userCart);
            }
            return UpdateCartDto.builder()
                    .item(id)
                    .status(200)
                    .quantity(cartItem.getQuantity())
                    .build();
        } else {
            userCart = new UserCart();
            userCart.setUserId(user);
            CartItem cartItem = new CartItem(null, id, 1, userCart);
            List<CartItem> list = List.of(cartItem);
            userCart.setItems(list);
            cartRepository.save(userCart);
            return UpdateCartDto.builder()
                    .item(id)
                    .status(200)
                    .quantity(cartItem.getQuantity())
                    .build();
        }
    }

    @Transactional
    public Response removeFromCart(Long id, String user) {
        UserCart userCart = cartRepository.findByUserId(user);
        if(userCart != null ) {
            List<CartItem> items = userCart.getItems();
            CartItem cartItem = new CartItem();
            cartItem.setItem(id);
            cartItem.setQuantity(0);
            if (items.contains(cartItem)) {
                List<CartItem> updatedCart = items.stream().peek(it -> {
                    if (it.getItem().equals(id)) {
                        it.setQuantity(it.getQuantity() - 1);
                        cartItem.setQuantity(it.getQuantity());
                    }
                }).collect(Collectors.toList());
                if(cartItem.getQuantity() == 0){
                    updatedCart = updatedCart.stream()
                            .filter(o -> !o.getItem().equals(id))
                            .collect(Collectors.toList());
                }
                userCart.setItems(updatedCart);
                cartRepository.save(userCart);

            }
            return UpdateCartDto.builder()
                    .item(id)
                    .status(200)
                    .quantity(cartItem.getQuantity())
                    .build();
        }
        return UpdateCartDto.builder()
                .item(id)
                .status(200)
                .quantity(0)
                .build();
    }

    public void updateCart(String sessionId, String username) {
        UserCart userCart = cartRepository.findByUserId(sessionId);
        if(userCart != null){
            userCart.setUserId(username);
            cartRepository.save(userCart);
        }
    }
}
