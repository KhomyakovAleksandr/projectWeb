package ru.rutmiit.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.dto.OrderCreateDto;
import ru.rutmiit.models.entities.Order;
import ru.rutmiit.models.entities.User;
import ru.rutmiit.models.entities.Rocket;
import ru.rutmiit.models.enums.OrderStatus;
import ru.rutmiit.repositories.OrderRepository;
import ru.rutmiit.repositories.UserRepository;
import ru.rutmiit.repositories.RocketRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RocketRepository rocketRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
                            RocketRepository rocketRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.rocketRepository = rocketRepository;
    }

    @Override
    @Transactional
    public void createOrder(OrderCreateDto orderDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Rocket rocket = rocketRepository.findById(orderDto.getRocketId()).orElseThrow();
        if (orderDto.getCargoWeight() > rocket.getPayloadCapacity()) {
            throw new IllegalArgumentException("Груз слишком тяжелый для этой ракеты!");
        }
        Order order = new Order();
        order.setUser(user);
        order.setRocket(rocket);
        order.setCargoName(orderDto.getCargoName());
        order.setCargoWeight(orderDto.getCargoWeight());
        order.setOrbitType(orderDto.getOrbitType());
        order.setStatus(OrderStatus.PENDING);
        order.setPhoneNumber(orderDto.getPhoneNumber());
        order.setContactEmail(orderDto.getContactEmail());

        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        boolean isModerator = user.getRoles().stream()
                .anyMatch(role -> role.getName().name().equals("MODERATOR"));

        if (isModerator) {
            return orderRepository.findAllByOrderByCreatedAtDesc();
        } else {
            return orderRepository.findAllByUserOrderByCreatedAtDesc(user);
        }
    }

    @Override
    @Transactional
    public void changeStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(newStatus);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}