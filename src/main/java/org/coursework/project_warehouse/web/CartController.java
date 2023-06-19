package org.coursework.project_warehouse.web;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CartEntity;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.model.User;
import org.coursework.project_warehouse.repository.OrderRepository;
import org.coursework.project_warehouse.repository.UserRepository;
import org.coursework.project_warehouse.service.CartService;
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
@RequestMapping("/cart")
public class CartController {

    private final UserRepository userRepository;
    private final CartService cartService;
    private final CableService cableService;
    private final OrderRepository orderRepository;

    @PostMapping("/buy")
    public ModelAndView buyCable(@RequestParam(name = "id") Integer id){
        ModelAndView modelAndView = new ModelAndView("redirect:/cable/buyer");

        User persona = userRepository.findByUsername("admin");

        CartEntity cart = cartService.createOrFind(persona);
        CableEntity cable = cableService.getCable(id);

        cartService.setCable(cart, cable);

//        cable.setQuantity(cable.getQuantity() - 1);
//        cableService.editCable(cable);

        return modelAndView;
    }

    @GetMapping
    public ModelAndView viewMenuCables() {
        List<CableEntity> allCable = cableService.getAllCable();
        ModelAndView modelAndView = new ModelAndView("pages/cart.html");
        modelAndView.addObject("allCable", allCable);
        return modelAndView;
    }
}
