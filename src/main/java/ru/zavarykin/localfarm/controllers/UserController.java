package ru.zavarykin.localfarm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zavarykin.localfarm.entity.Role;
import ru.zavarykin.localfarm.entity.User;
import ru.zavarykin.localfarm.repository.RoleRepository;
import ru.zavarykin.localfarm.repository.UserRepository;

import java.util.*;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/users")
    public String userList(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "user-list";
    }

    @GetMapping("/users/{id}")
    public String userDetails(@PathVariable(value = "id") int id, Model model){
        if(!userRepository.existsById(id)){
            return "redirect:/users";
        }
        Optional<User> user = userRepository.findById(id);
        List<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "user-details";
    }

    @GetMapping("/users/{id}/edit")
    public String userEdit(@PathVariable(value = "id") int id, Model model){
        if(!userRepository.existsById(id)){
            return "redirect:/users";
        }
        Optional<User> user = userRepository.findById(id);
        List<User> res = new ArrayList<>();
        List<Role> rol = new ArrayList<>();
        rol = roleRepository.findAll();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        model.addAttribute("roles", rol);
        return "user-edit";
    }

    @PostMapping("/users/{id}/edit")
    public String userPostUpdate(@PathVariable(value = "id") int id,
                                 @RequestParam String name,
                                 @RequestParam List<String>role){
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(name);
        user.getRoles().clear();
        List<Role> roles = roleRepository.findAll();
        for (Role role1 : roles){
            if (role.contains(role1.getName())){
                user.getRoles().add(role1);
            }
        }
        userRepository.save(user);
        return "redirect:/users";
    }



}
