package org.coursework.project_warehouse.web;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CableToCartEntity;
import org.coursework.project_warehouse.dto.CartEntity;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.model.User;
import org.coursework.project_warehouse.repository.CableToCartRepository;
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

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

@Controller
@RequestMapping("/cart")
public class CartController {

    private final UserRepository userRepository;
    private final CartService cartService;
    private final CableService cableService;
    private final OrderRepository orderRepository;
    private final CableToCartRepository cableToCartRepository;

    @PostMapping("/buy")
    public ModelAndView buyCable(@RequestParam(name = "id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/cable/buyer");

        User persona = userRepository.findByUsername("admin");

        CartEntity cart = cartService.createOrFindWorkCart(persona);
        CableEntity cable = cableService.getCable(id);

        cartService.setCable(cart, cable);

        return modelAndView;
    }

    @GetMapping
    public ModelAndView viewMenuCables() {
        ModelAndView modelAndView = new ModelAndView("pages/cart.html");

        User persona = userRepository.findByUsername("admin");
        CartEntity cart = cartService.createOrFindWorkCart(persona);

        List<CableToCartEntity> cablesToCarts = cart.getCablesToCarts();
        List<CableEntity> allCable = createListCable(cablesToCarts);

        Double total = 0.00;
        for (CableEntity cable : allCable) {
            total += (cable.getPrice() * cable.getQuantity());
        }
        modelAndView.addObject("total", total);
        modelAndView.addObject("allCable", allCable);
        return modelAndView;
    }

    public List<CableEntity> createListCable(List<CableToCartEntity> cableToCartEntities) {
        List<CableEntity> allCable = new ArrayList<>();
        for (int i = 0; i < cableToCartEntities.size(); i++) {
            CableEntity cable = cableToCartEntities.get(i).getCable();
            cable.setQuantity(cableToCartEntities.get(i).getQuantity());
            allCable.add(cable);
        }
        return allCable;
    }
}
