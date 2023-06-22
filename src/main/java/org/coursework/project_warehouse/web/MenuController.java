package org.coursework.project_warehouse.web;

import lombok.RequiredArgsConstructor;
import org.coursework.project_warehouse.dto.CableEntity;
import org.coursework.project_warehouse.dto.ProductEntity;
import org.coursework.project_warehouse.model.User;
import org.coursework.project_warehouse.repository.ProductRepository;
import org.coursework.project_warehouse.repository.UserRepository;
import org.coursework.project_warehouse.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final UserService service;


    @GetMapping
    public String viewMenu() {
        return "pages/menu.html";
    }

    @GetMapping("/admin")
    public String viewMenuAdmin() {
        return "pages/menuAdmin.html";
    }

    @GetMapping("/loginpage")
    public String login() {
        return "pages/login-page.html";
    }

    @PostMapping("/create")
    public String addNewUser(@Valid @ModelAttribute(name = "newUser") User newUser, BindingResult result) {

        if (result.hasErrors()){
            return "pages/login-page.html";
        }

        if (!result.hasErrors()) {
            service.createPersona(newUser.getUsername(), newUser.getPassword());
        }
        return "pages/login-page.html";
    }

//    @PostMapping("/create")
//    public String create(@RequestParam(name = "username") String username,
//                         @RequestParam(name = "password") String password) {
//        service.createPersona(username, password);
//        return "login-page.html";
//    }

}
