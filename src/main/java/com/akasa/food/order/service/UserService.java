package com.akasa.food.order.service;

import com.akasa.food.order.dto.*;
import com.akasa.food.order.models.*;
import com.akasa.food.order.models.enums.OrderStatus;
import com.akasa.food.order.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private UserCartRepository cartRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private FoodItemService foodItemService;

    public Response getFlights(String user) {
        log.info("Get Flights request for user :: {}", user);
        List<Flight>  flights= flightRepo.findAll();
        if(flights.isEmpty()){
            log.info("Categories not found in the DB");
            return ErrorResponse.builder()
                    .status("500")
                    .error("Empty data")
                    .message("Please try after some time")
                    .build();
        }
        log.info("Flights list :: {}", flights);
        return GetFlightsResponse.builder()
                .status("200")
                .message("Success")
                .flights(flights)
                .build();
    }

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

    public Response addCarts(String user, Set<CartItem> items) {
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

    public Response completeOrder(String user, Order order) {
        log.info("Order request received for user :: {}, order ::{}", user, order);
        String validated = validateOrder(order);
        if(validated.equalsIgnoreCase("")) {
            String orderId = UUID.randomUUID().toString();
            order.setOrderId(orderId);
            order.setUserId(user);
            order.setStatus(OrderStatus.PROCESSING.toString());

            cartRepository.deleteByUserId(user);

            orderRepository.save(order);

            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

            scheduledExecutorService.schedule(() -> task(orderId), 1, TimeUnit.MINUTES);
            return OrderResponse.builder()
                    .orderId(orderId)
                    .message("Order Successfully Placed")
                    .status("200").build();
        } else {
            validated = validated + "Sorry bit late items out of stock.";
            return ErrorResponse.builder()
                    .status("400")
                    .message(validated)
                    .build();
        }
    }

    private String validateOrder(Order order) {
        List<Long> list = order.getItems().stream()
                .map(CartItem::getItem)
                .collect(Collectors.toList());

        List<Inventory> inventoryList = inventoryRepository.findAllByItemIdIn(list);
        Map<Long, Inventory> map = inventoryList
                .stream().collect(Collectors.toMap(Inventory::getItemId, Function.identity()));
        StringBuilder stringBuilder = new StringBuilder();

        List<Inventory> updatedInventory = new ArrayList<>();

        order.getItems().forEach(li->{
            Inventory inventory = map.get(li.getItem());
            if(map.containsKey(li.getItem()) && inventory.getStock() < li.getQuantity()) {
                log.info("Item no in stock :: {}", inventory.getItemName());
                stringBuilder.append(inventory.getItemName()).append(", ");
            }
            else{
                inventory.setStock(inventory.getStock() - li.getQuantity());
                updatedInventory.add(inventory);
            }

        });

        String res = new String(stringBuilder);
        if(res.equalsIgnoreCase("")){
            inventoryRepository.saveAll(updatedInventory);
        }

        return res;


    }

    private void task (String orderId) {
        Order order = orderRepository.findByOrderId(orderId);

        order.setStatus(OrderStatus.COMPLETED.toString());

        orderRepository.save(order);
    }

    public Response getAllOrders(String user) {

        List<Order> orders = orderRepository.findAllByUserId(user);

        if(orders.isEmpty()){
            return ErrorResponse.builder()
                    .status("500").error("No data").build();
        }

        return AllOrderResponse.builder()
                .status("200")
                .message("Success")
                .orders(orders)
                .build();

    }

    public Response getUserCartDetails(String user) {

        UserCart cart =  cartRepository.findByUserId(user);

        if(cart == null || cart.getItems() == null || cart.getItems().isEmpty()){
            log.info("Categories not found in the DB");
            return ErrorResponse.builder()
                    .status("500")
                    .error("Empty data")
                    .message("Please try after some time")
                    .build();
        }

        Response response = foodItemService.selectedItems(cart.getItems());

        log.info("User Cart :: {}", response);
        return response;

    }
}
