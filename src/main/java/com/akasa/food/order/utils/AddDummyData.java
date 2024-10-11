package com.akasa.food.order.utils;

import com.akasa.food.order.models.*;
import com.akasa.food.order.repository.*;
import com.akasa.food.order.utils.builders.UserBuilder;
import com.akasa.food.order.utils.builders.UserRoleBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class AddDummyData {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private FoodItemRepo foodItemRepo;

    @Autowired
    private ItemCategoryRepo itemCategoryRepo;

    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private UserCartRepository uSerCartRepository;

    @Autowired
    private OrderRepository orderRepository;

    //@PostConstruct
    //@Transactional
    private void startUp() {

        UserRole userRole1 = UserRoleBuilder.builder()
                .name("ROLE_USER")
                .build();
        UserRole userRole2 = UserRoleBuilder.builder()
                .name("ROLE_ADMIN")
                .build();
        roleRepo.saveAll(new HashSet<>(Arrays.asList(userRole1, userRole2)));

        userRole1.setId(1L);

        User user = UserBuilder.builder()
                .email("testuser@gmail.com")
                .roles(new HashSet<>(Arrays.asList(userRole1)))
                .username("testuser@gmail.com")
                .accountNonExpired(true)
                .password(passwordEncoder.encode("testpass"))
                .build();

        userRepo.save(user);

        Flight flight1 = new Flight();
        flight1.setFlightId("QP 2313");
        flight1.setUserId("testuser@gmail.com");
        flight1.setPath("Pune To Mumbai");

        Flight flight2 = new Flight();
        flight2.setFlightId("QP 1248");
        flight2.setUserId("testuser@gmail.com");
        flight2.setPath("Mumbai To Pune");

        Flight flight3 = new Flight();
        flight3.setFlightId("QP 1982");
        flight3.setUserId("testuser@gmail.com");
        flight3.setPath("Mumbai To Bangalore");

        Flight flight4 = new Flight();
        flight4.setFlightId("QP 2456");
        flight4.setUserId("testuser@gmail.com");
        flight4.setPath("Bangalore to Mumbai");

        flightRepo.saveAll(Arrays.asList(flight2, flight1, flight3, flight4));

        itemCategoryRepo.saveAll(DummyData.getCats());
        inventoryRepository.saveAll(DummyData.getInv());
        foodItemRepo.saveAll(DummyData.getfoods());

        List<CartItem> cartItems= new LinkedList<>();
        CartItem cartItem = new CartItem();

        cartItem.setItem(1L);
        cartItem.setQuantity(5);

        CartItem cartItem1 = new CartItem();

        cartItem1.setItem(1L);
        cartItem1.setQuantity(10);

        cartItems.add(cartItem1);
        cartItems.add(cartItem);
        UserCart cart = new UserCart();
        cart.setUserId("testuser@gmail.com");
        cart.setItems(cartItems);
        uSerCartRepository.deleteAll();
        //orderRepository.deleteAll();
        //uSerCartRepository.save(cart);


    }

    private int generateQty() {
        Random random = new Random();
        return random.nextInt(11);
    }
}
