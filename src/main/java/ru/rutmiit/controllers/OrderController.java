package ru.rutmiit.controllers;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.dto.OrderCreateDto;
import ru.rutmiit.models.entities.Rocket;
import ru.rutmiit.models.enums.OrderStatus;
import ru.rutmiit.repositories.RocketRepository;
import ru.rutmiit.services.OrderService;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final RocketRepository rocketRepository;

    public OrderController(OrderService orderService, RocketRepository rocketRepository) {
        this.orderService = orderService;
        this.rocketRepository = rocketRepository;
    }

    @GetMapping
    public String listOrders(Principal principal, Model model) {
        model.addAttribute("orders", orderService.getAllOrders(principal.getName()));
        return "orders"; // Твой orders.html
    }


    @GetMapping("/create/{rocketId}")
    public String showCreateForm(@PathVariable Long rocketId, Model model) {
        Rocket rocket = rocketRepository.findById(rocketId)
                .orElseThrow(() -> new IllegalArgumentException("Ракета не найдена"));

        OrderCreateDto dto = new OrderCreateDto();
        dto.setRocketId(rocketId);

        model.addAttribute("orderCreateDto", dto);
        model.addAttribute("maxWeight", rocket.getPayloadCapacity());

        return "order-create";
    }

    @PostMapping("/create")
    public String createOrder(@Valid OrderCreateDto orderCreateDto,
                              BindingResult bindingResult,
                              Principal principal,
                              Model model) {

        if (bindingResult.hasErrors()) {
            Rocket rocket = rocketRepository.findById(orderCreateDto.getRocketId()).orElseThrow();
            model.addAttribute("maxWeight", rocket.getPayloadCapacity());
            return "order-create";
        }

        try {
            orderService.createOrder(orderCreateDto, principal.getName());
        } catch (IllegalArgumentException e) {
            Rocket rocket = rocketRepository.findById(orderCreateDto.getRocketId()).orElseThrow();
            model.addAttribute("maxWeight", rocket.getPayloadCapacity());
            model.addAttribute("weightError", e.getMessage());
            return "order-create";
        }

        return "redirect:/orders";
    }


    @PostMapping("/status/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public String changeStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        orderService.changeStatus(id, status);
        return "redirect:/orders";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public String deleteOrder(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        orderService.deleteOrder(id);
        redirectAttributes.addFlashAttribute("message", "Заказ #" + id + " успешно удален");
        return "redirect:/orders";
    }
}