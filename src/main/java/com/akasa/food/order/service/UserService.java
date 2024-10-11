package com.akasa.food.order.service;

import com.akasa.food.order.dto.*;
import com.akasa.food.order.models.*;
import com.akasa.food.order.models.enums.OrderStatus;
import com.akasa.food.order.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


    @Transactional
    public Response completeOrder(String user, Order order) {
        log.info("Order request received for user :: {},", user);
        UserCart userCart = cartRepository.findByUserId(user);
        String validated = validateOrder(userCart);
        if(validated.equalsIgnoreCase("")) {

            List<OrderItem> orderItems = userCart.getItems().stream()
                    .map(e -> new OrderItem(null, e.getItem(), e.getQuantity(), order))
                    .collect(Collectors.toList());
            order.setItems(orderItems);
            String orderId = UUID.randomUUID().toString();
            order.setUserId(user);
            order.setStatus(OrderStatus.PROCESSING.toString());
            order.setOrderId(orderId);
            cartRepository.deleteByUserId(user);

            Order saved = orderRepository.save(order);

            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

            scheduledExecutorService.schedule(() -> task(saved.getId()), 1, TimeUnit.MINUTES);
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

    private String validateOrder(UserCart userCart) {
        List<Long> list = userCart.getItems().stream()
                .map(CartItem::getItem)
                .collect(Collectors.toList());

        List<Inventory> inventoryList = inventoryRepository.findAllByItemIdIn(list);
        Map<Long, Inventory> map = inventoryList
                .stream().collect(Collectors.toMap(Inventory::getItemId, Function.identity()));
        StringBuilder stringBuilder = new StringBuilder();

        List<Inventory> updatedInventory = new ArrayList<>();

        userCart.getItems().forEach(li->{
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

    private void task (Long orderId) {
        Order order = orderRepository.findById(orderId).get();

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


}
