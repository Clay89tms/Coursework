package org.coursework.project_warehouse.web;

import org.coursework.project_warehouse.Dto.ProductEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

public interface ProductController {

    public ModelAndView viewMenu(@ModelAttribute(name = "product") ProductEntity product);
    public String addProduct(ProductEntity product);
}
