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

    @GetMapping
    public ModelAndView viewMenu(@ModelAttribute(name = "product") ProductEntity product) {
        var allProducts = repository.findAll();

        ModelAndView modelAndView = new ModelAndView("menu.html");
        modelAndView.addObject("allProduct", allProducts);

        return modelAndView;
    }

    @PostMapping(name = "/add")
    public String addProduct(ProductEntity product) {
        repository.save(product);
        return "redirect:/";
    }

//    @PostMapping(name = "/new")
//    public ModelAndView addNewProduct(@PathVariable(name = "id" , required = false) Integer id){
//        return new ModelAndView("tabelAddProduct.html");
//    }

}
