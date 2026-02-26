package ru.rutmiit.services;

import ru.rutmiit.dto.OrderCreateDto;
import ru.rutmiit.models.entities.Order;
import ru.rutmiit.models.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    // Создать новый заказ от имени пользователя
    void createOrder(OrderCreateDto orderDto, String username);

    // Получить список заказов (для юзера — свои, для модера — все)
    List<Order> getAllOrders(String username);

    // Сменить статус заказа (только для модератора)
    void changeStatus(Long orderId, OrderStatus newStatus);
}