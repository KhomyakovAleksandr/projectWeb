package ru.rutmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rutmiit.models.entities.Order;
import ru.rutmiit.models.entities.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Найти все заказы конкретного пользователя, сортируя по дате (новые сверху)
    List<Order> findAllByUserOrderByCreatedAtDesc(User user);

    // Найти вообще все заказы для модератора, тоже с сортировкой
    List<Order> findAllByOrderByCreatedAtDesc();
}