package org.coursework.project_warehouse.web;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.service.CableService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/additional/add")
    public ModelAndView viewPageAddNewCable(@ModelAttribute(name = "newCable") CableEntity cable) {
        ModelAndView modelAndView = new ModelAndView("pages/additional/addNewCable.html");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addNewCables(@Valid @ModelAttribute(name = "newCable")CableEntity cable, BindingResult result) {

        if (!result.hasErrors()) {
            service.save(cable);
        }
        if (result.hasErrors()){
            return "pages/additional/addNewCable.html";
        }
        return "redirect:/cable/additional/add";
    }

    @PostMapping("/delete")
    public String deleteCable(Integer id){
        service.deleteCable(id);
        return "redirect:/cable";
    }

    @GetMapping("/additional/edit")
    public ModelAndView viewPageEditCable(Integer id) {
        CableEntity cable = service.getCable(id);
        ModelAndView modelAndView = new ModelAndView("pages/additional/editCable.html");
        modelAndView.addObject("editCable", cable);
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editCable(@Valid @ModelAttribute(name = "editCable")CableEntity cable, BindingResult result) {

        if (!result.hasErrors()) {
//            service.updateCable(cable)
            service.save(cable);
        }
        if (result.hasErrors()) {
            return "pages/additional/editCable.html";
        }
        return "redirect:/cable";
    }
}
