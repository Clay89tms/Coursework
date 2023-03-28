package org.coursework.project_warehouse.web.impl;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.Dto.ProductEntity;
import org.coursework.project_warehouse.repository.ProductRepository;
import org.coursework.project_warehouse.web.ProductController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class ProductControllerImpl {

    private final ProductRepository repository;

    @GetMapping("/menu")
    public ModelAndView viewMenu(@ModelAttribute(name = "newproduct") ProductEntity product) {
        var allProducts = repository.findAll();

        ModelAndView modelAndView = new ModelAndView("menu.html");
        modelAndView.addObject("allProduct", allProducts);

        return modelAndView;
    }

    @PostMapping
    public String addProduct(ProductEntity product) {
        repository.save(product);
        return "redirect:/menu";

    }

    @GetMapping
    public ModelAndView addNewProduct(@ModelAttribute(name = "newproduct") ProductEntity product){
        ModelAndView modelAndView = new ModelAndView("addnew.html");
        return modelAndView;
    }

}
