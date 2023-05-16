package org.coursework.project_warehouse.web;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.ProductEntity;
import org.coursework.project_warehouse.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final ProductRepository repository;

    @GetMapping
    public String viewMenu() {
        return "pages/menu.html";
    }





//    @GetMapping("/menu")
//    public ModelAndView viewMenu(@ModelAttribute(name = "newproduct") ProductEntity product) {
//        var allProducts = repository.findAll();
//
//        ModelAndView modelAndView = new ModelAndView("menu.html");
//        modelAndView.addObject("allProduct", allProducts);
//
//        return modelAndView;
//    }

    @PostMapping
    public String addProduct(ProductEntity product) {
        repository.save(product);
        return "redirect:/menu";

    }

    @GetMapping("old")
    public ModelAndView addNewProduct(@ModelAttribute(name = "newproduct") ProductEntity product) {
        ModelAndView modelAndView = new ModelAndView("addnew.html");
        return modelAndView;
    }

    @GetMapping("/go")
    public ModelAndView addNewProduct() {
        ModelAndView modelAndView = new ModelAndView("clay_warehouse_menu.html");
        return modelAndView;
    }

//    @GetMapping
//    public String viewCasePage() {
//        return "redirect:/casePage";
//    }
//
//    @GetMapping
//    public String viewCablePage() {
//        return "redirect:/cablePage";
//    }
//
//    @GetMapping
//    public String viewChargingPage() {
//        return "redirect:/chargingPage";
//    }

}
