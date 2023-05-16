package org.coursework.project_warehouse.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor

@Controller
@RequestMapping("/charging")
public class ChargingController {

    @GetMapping
    public String viewMenuCharging(){
        return "pages/chargingPage.html";
    }
}
