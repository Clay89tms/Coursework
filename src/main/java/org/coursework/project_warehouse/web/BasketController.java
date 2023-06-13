package org.coursework.project_warehouse.web;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.BasketEntity;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.model.User;
import org.coursework.project_warehouse.repository.BasketRepository;
import org.coursework.project_warehouse.repository.CableRepository;
import org.coursework.project_warehouse.repository.OrderRepository;
import org.coursework.project_warehouse.repository.UserRepository;
import org.coursework.project_warehouse.service.BasketService;
import org.coursework.project_warehouse.service.CableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor

@Controller
@RequestMapping("/basket")
public class BasketController {

    private final UserRepository userRepository;
    private final BasketService basketService;
    private final CableService cableService;
    private final OrderRepository orderRepository;


    @PostMapping("buy")
    public ModelAndView buyCable(@RequestParam(name = "id") Integer id){
        ModelAndView modelAndView = new ModelAndView("redirect:/cable/buyer");

        User persona = userRepository.findByUsername("admin");
        BasketEntity basket = basketService.createOrFind(persona);

        CableEntity cable = cableService.getCable(id);

        basketService.setCable(basket, cable);

        cable.setQuantity(cable.getQuantity() - 1);
        cableService.editCable(cable);

        return modelAndView;
    }

    @GetMapping
    public ModelAndView viewMenuCables() {
        List<CableEntity> allCable = cableService.getAllCable();
        ModelAndView modelAndView = new ModelAndView("pages/basket.html");
        modelAndView.addObject("allCable", allCable);
        return modelAndView;
    }
}
