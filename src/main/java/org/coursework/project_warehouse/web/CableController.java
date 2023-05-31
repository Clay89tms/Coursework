package org.coursework.project_warehouse.web;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.service.CableService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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
    public ModelAndView viewPageAddNewCable(@ModelAttribute(name = "newcable") CableEntity cable) {
        ModelAndView modelAndView = new ModelAndView("pages/additional/addNewCable.html");




        return modelAndView;
    }

    @PostMapping
    public String addNewCables(@Valid CableEntity cable, BindingResult result, RedirectAttributes atts) {

        if (!result.hasErrors()) {
            service.save(cable);
        }
        if (result.hasErrors()){
            return "pages/additional/addNewCable.html";
        }
        return "redirect:/cable/additional/add";
    }

//    @PostMapping
//    public ModelAndView addNewCables(@Valid CableEntity cable, BindingResult result) {
//        ModelAndView modelAndView = new ModelAndView("pages/additional/addNewCable.html");
//
//        if (result.hasErrors()) {
//            HashMap<String, String> errors = new HashMap<>();
//            List<FieldError> fieldErrors = result.getFieldErrors();
//
//            for (FieldError fieldError : fieldErrors){
//                errors.put("err_" + fieldError.getField(), fieldError.getDefaultMessage());
//            }
//            modelAndView.addAllObjects(errors);
//        } else {
//            service.save(cable);
////            return modelAndView.set("redirect:/cable/additional/add");
//        }
//        return modelAndView;
//    }
}
