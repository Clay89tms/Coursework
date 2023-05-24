package org.coursework.project_warehouse.web;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.service.CableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor

@Controller
@RequestMapping("/cable")
public class CableController {

    private final CableService service;

    @GetMapping
    public ModelAndView viewMenuCables() {
        List<CableEntity> allCable = service.getAllCable();
        ModelAndView modelAndView = new ModelAndView("pages/cablePage.html");
        modelAndView.addObject("allCable", allCable);
        return modelAndView;
    }

    @PostMapping
    public String addNewCables(CableEntity cable) {
        return "";
    }
}
