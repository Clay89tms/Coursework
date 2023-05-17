package org.coursework.project_warehouse.web;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CableEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor

@Controller
@RequestMapping("/cable")
public class CableController {

    @GetMapping
    public String viewMenuCable() {
        return "pages/cablePage.html";
    }

    @PostMapping
    public String addNewCable(CableEntity cable) {
        return "";
    }
}
