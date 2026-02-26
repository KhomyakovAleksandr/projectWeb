package ru.rutmiit.controllers;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.dto.OrderCreateDto;
import ru.rutmiit.models.enums.OrderStatus;
import ru.rutmiit.services.OrderService;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 1. Страница списка заказов
    @GetMapping
    public String listOrders(Principal principal, Model model) {
        model.addAttribute("orders", orderService.getAllOrders(principal.getName()));
        return "orders"; // Твой orders.html
    }

    // 2. Показ формы бронирования (GET)
    @GetMapping("/create/{rocketId}")
    public String showCreateForm(@PathVariable Long rocketId, Model model) {
        OrderCreateDto dto = new OrderCreateDto();
        dto.setRocketId(rocketId);
        model.addAttribute("orderCreateDto", dto);
        return "order-create";
    }

    // 3. Обработка формы бронирования (POST)
    @PostMapping("/create")
    public String createOrder(@Valid OrderCreateDto orderCreateDto,
                              BindingResult bindingResult,
                              Principal principal,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "order-create";
        }
        orderService.createOrder(orderCreateDto, principal.getName());
        return "redirect:/orders";
    }

    // 4. Смена статуса (только для модератора)
    @PostMapping("/status/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public String changeStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        orderService.changeStatus(id, status);
        return "redirect:/orders";
    }
}