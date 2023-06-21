package org.coursework.project_warehouse.web;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CartEntity;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.dto.CaseEntity;
import org.coursework.project_warehouse.dto.ChargerEntity;
import org.coursework.project_warehouse.model.User;
import org.coursework.project_warehouse.repository.CableToCartRepository;
import org.coursework.project_warehouse.repository.OrderRepository;
import org.coursework.project_warehouse.repository.UserRepository;
import org.coursework.project_warehouse.service.CartService;
import org.coursework.project_warehouse.service.CableService;
import org.coursework.project_warehouse.service.CaseService;
import org.coursework.project_warehouse.service.ChargerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor

@Controller
@RequestMapping("/cart")
public class CartController {

    private final UserRepository userRepository;
    private final CartService cartService;
    private final CableService cableService;
    private final CaseService caseService;
    private final ChargerService chargerService;
    private final OrderRepository orderRepository;

    @PostMapping("/toCart")
    public ModelAndView buyCable(@RequestParam(name = "id") Integer id, @RequestParam(name = "type") String type) {
        ModelAndView modelAndView = new ModelAndView();
//
        User persona = userRepository.findByUsername("admin");
        CartEntity cart = cartService.createOrFindWorkCart(persona);
//

        if (type != null) {
            if ("cable".equalsIgnoreCase(type)) {
                CableEntity cable = cableService.getCable(id);
                cartService.setCable(cart, cable);
                modelAndView.setViewName("redirect:/cable/buyer");
            } else if ("case".equalsIgnoreCase(type)) {
                CaseEntity aCase = caseService.getCase(id);
                cartService.setCase(cart, aCase);
                modelAndView.setViewName("redirect:/case/buyer");
            } else if ("charger".equalsIgnoreCase(type)) {
                ChargerEntity charger = chargerService.getCharger(id);
                cartService.setCharger(cart, charger);
                modelAndView.setViewName("redirect:/charger/buyer");
            }
        }


        return modelAndView;
    }

    @GetMapping
    public ModelAndView viewMenuCart() {
        ModelAndView modelAndView = new ModelAndView("pages/cart.html");
//
        User persona = userRepository.findByUsername("admin");
        CartEntity cart = cartService.createOrFindWorkCart(persona);
//
        modelAndView = cartService.createListCart(cart, modelAndView);

        return modelAndView;
    }

    @PostMapping("/remove")
    public String removeProduct(@RequestParam(name = "id") Integer id, @RequestParam(name = "type") String type) {
//
        User persona = userRepository.findByUsername("admin");
        CartEntity cart = cartService.createOrFindWorkCart(persona);
//
        cartService.removeProduct(cart, id, type);
        return "redirect:/cart";
    }

    @PostMapping("/buy")
    public String buyOrder() {

        User persona = userRepository.findByUsername("admin");

        CartEntity cart = cartService.createOrFindWorkCart(persona);

        cartService.doOrder(cart);
        return "redirect:/menu";
    }

    @GetMapping("/allOrders")
    public ModelAndView viewAllOrder() {
        ModelAndView modelAndView = new ModelAndView("pages/allOrders.html");

        User persona = userRepository.findByUsername("admin");
        modelAndView = cartService.getAllOrders(persona, modelAndView);

        return modelAndView;
    }
}
