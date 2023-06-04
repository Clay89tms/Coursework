package org.coursework.project_warehouse.web;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CaseEntity;
import org.coursework.project_warehouse.service.CaseService;
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
@RequestMapping("/case")
public class CaseController {

    private final CaseService service;

    @GetMapping
    public ModelAndView viewMenuCase() {
        List<CaseEntity> allCase = service.getAllCase();
        ModelAndView modelAndView = new ModelAndView("pages/casePage.html");
        modelAndView.addObject("allCase", allCase);
        return modelAndView;
    }

    @GetMapping("/additional/add")
    public ModelAndView viewPageAddNewCase(@ModelAttribute(name = "newCase") CaseEntity caseEntity) {
        ModelAndView modelAndView = new ModelAndView("pages/additional/addNewCase.html");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addNewCase(@Valid @ModelAttribute(name = "newCase")CaseEntity caseEntity, BindingResult result) {

        if (result.hasErrors()){
            return "pages/additional/addNewCase.html";
        }

        if (!result.hasErrors()) {
            service.save(caseEntity);
        }
        return "redirect:/case/additional/add";
    }

    @PostMapping("/delete")
    public String deleteCase(Integer id){
        service.deleteCase(id);
        return "redirect:/case";
    }

    @GetMapping("/additional/edit")
    public ModelAndView viewPageEditCase(Integer id) {
        CaseEntity caseEntity = service.getCase(id);
        ModelAndView modelAndView = new ModelAndView("pages/additional/editCase.html");
        modelAndView.addObject("editCase", caseEntity);
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editCase(@Valid @ModelAttribute(name = "editCase")CaseEntity caseEntity, BindingResult result) {

        if (!result.hasErrors()) {
            service.editCase(caseEntity);
        }

        if (result.hasErrors()) {
            return "pages/additional/editCase.html";
        }
        return "redirect:/case";
    }
}
