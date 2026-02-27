package ru.rutmiit.services;

import ru.rutmiit.dto.OrderCreateDto;
import ru.rutmiit.models.entities.Order;
import ru.rutmiit.models.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    void createOrder(OrderCreateDto orderDto, String username);

    List<Order> getAllOrders(String username);

    void changeStatus(Long orderId, OrderStatus newStatus);

    void deleteOrder(Long id);
}