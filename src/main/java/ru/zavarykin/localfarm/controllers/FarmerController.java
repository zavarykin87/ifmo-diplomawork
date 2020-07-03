package ru.zavarykin.localfarm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.zavarykin.localfarm.entity.Product;
import ru.zavarykin.localfarm.repository.FarmerRepository;

@Controller
public class FarmerController {

    @Autowired
    FarmerRepository farmerRepository;

    @GetMapping("/farmers")
    public String farmer(Model model){
        //Iterable<Product> farmers = farmerRepositoru.findAll();
        //model.addAttribute("farmers", farmers);
        return "farmers";
    }
}
