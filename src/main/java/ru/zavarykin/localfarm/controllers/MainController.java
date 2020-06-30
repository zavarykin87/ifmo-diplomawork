package ru.zavarykin.localfarm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class MainController {

    @GetMapping("/")
    public String home (Model model) {
        return "index";
    }


}
