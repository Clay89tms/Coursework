package org.coursework.project_warehouse.web;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CaseEntity;
import org.coursework.project_warehouse.dto.ChargerEntity;
import org.coursework.project_warehouse.service.ChargerService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor

@Controller
@RequestMapping("/charger")
public class ChargerController {

    private final ChargerService service;

    @GetMapping
    public ModelAndView viewMenuCharger() {
        List<ChargerEntity> allCharger = service.getAllCharger();
        ModelAndView modelAndView = new ModelAndView("pages/chargerPage.html");
        modelAndView.addObject("allCharger", allCharger);
        return modelAndView;
    }

    @GetMapping("/additional/add")
    public ModelAndView viewPageAddNewCharger(@ModelAttribute(name = "newCharger") ChargerEntity chargerEntity) {
        ModelAndView modelAndView = new ModelAndView("pages/additional/addNewCharger.html");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addNewCharger(@Valid @ModelAttribute(name = "newCharger")ChargerEntity chargerEntity, BindingResult result) {

        if (result.hasErrors()){
            return "pages/additional/addNewCharger.html";
        }

        if (!result.hasErrors()) {
            service.save(chargerEntity);
        }
        return "redirect:/charger/additional/add";
    }

    @PostMapping("/delete")
    public String deleteCharger(Integer id){
        service.deleteCharger(id);
        return "redirect:/charger";
    }

    @GetMapping("/additional/edit")
    public ModelAndView viewPageEditCharger(Integer id) {
        ChargerEntity chargerEntity = service.getCharger(id);
        ModelAndView modelAndView = new ModelAndView("pages/additional/editCharger.html");
        modelAndView.addObject("editCharger", chargerEntity);
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editCharger(@Valid @ModelAttribute(name = "editCharger")ChargerEntity chargerEntity, BindingResult result) {

        if (!result.hasErrors()) {
            service.editCharger(chargerEntity);
        }

        if (result.hasErrors()) {
            return "pages/additional/editCharger.html";
        }
        return "redirect:/charger";
    }
}
