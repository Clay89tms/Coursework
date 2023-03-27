package org.coursework.project_warehouse.web.impl;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.Dto.ProductEntity;
import org.coursework.project_warehouse.repository.ProductRepository;
import org.coursework.project_warehouse.web.ProductController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class ProductControllerImpl implements ProductController {

    private final ProductRepository repository;

    @GetMapping
    public ModelAndView viewMenu(@ModelAttribute(name = "product") ProductEntity product) {
        var allProducts = repository.findAll();

        ModelAndView modelAndView = new ModelAndView("menu.jsp");
        modelAndView.addObject("allProduct", allProducts);

        return modelAndView;
    }

    @PostMapping
    public String addProduct(ProductEntity product) {
        repository.save(product);
        return "redirect:/";
    }

}
