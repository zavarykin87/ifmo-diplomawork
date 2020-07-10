package ru.zavarykin.localfarm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.zavarykin.localfarm.entity.Role;
import ru.zavarykin.localfarm.entity.User;
import ru.zavarykin.localfarm.repository.RoleRepository;
import ru.zavarykin.localfarm.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FarmerController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/farmers")
    public String getFarmers(Model model){
        List <User> farmers = userRepository.findByRoles(roleRepository.findById(2));
        model.addAttribute("farmers", farmers);
        return "farmers";
    }
}
