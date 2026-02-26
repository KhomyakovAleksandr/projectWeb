package ru.rutmiit.controllers;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.dto.RocketAddDto;
import ru.rutmiit.dto.RocketDTO;
import ru.rutmiit.services.RocketService;

import java.util.List;

@Controller
@RequestMapping("/rockets")
public class RocketController {

    private final RocketService rocketService; // Работаем через интерфейс

    public RocketController(RocketService rocketService) {
        this.rocketService = rocketService;
    }



    @GetMapping("/add")
    public String addRocket(Model model) {
        if (!model.containsAttribute("rocketAddDto")) {
            model.addAttribute("rocketAddDto", new RocketAddDto());
        }
        return "rocket-add";
    }

    @PostMapping("/add")
    public String doAddRocket(@Valid @ModelAttribute("rocketAddDto") RocketAddDto rocketAddDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("rocketAddDto", rocketAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.rocketAddDto", bindingResult);
            return "redirect:/rockets/add";
        }

        rocketService.addRocket(rocketAddDto);
        return "redirect:/rockets";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("rocket", rocketService.getRocketById(id));
        return "rocket-details";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public String deleteRocket(@PathVariable Long id) {
        rocketService.deleteRocket(id);
        return "redirect:/rockets";
    }

    @GetMapping
    public String listRockets(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<RocketDTO> rockets;


        if (keyword != null && !keyword.trim().isEmpty()) {
            rockets = rocketService.searchRockets(keyword);
        } else {
            rockets = rocketService.getAllRockets();
        }

        model.addAttribute("rockets", rockets);
        model.addAttribute("keyword", keyword);

        return "rockets";
    }
}