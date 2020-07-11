package ru.zavarykin.localfarm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zavarykin.localfarm.entity.Product;
import ru.zavarykin.localfarm.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String product(Model model){
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/add")
    @PreAuthorize("hasAuthority ('ROLE_FARMER')")
    public String productAdd(Model model) {
        return "products-add";
    }

    @PostMapping("/products/add")
    @PreAuthorize("hasAuthority ('ROLE_FARMER')")
    public String productPostAdd(@RequestParam String name, @RequestParam String price, @RequestParam String description,  Model model){
        Product product = new Product(name, Integer.parseInt(price), description);
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String productDetails(@PathVariable(value = "id") int id,  Model model){
        if(!productRepository.existsById(id)){
            return "redirect:/products";
        }
        Optional<Product> product = productRepository.findById(id);
        List<Product> res = new ArrayList<>();
        product.ifPresent(res::add);
        model.addAttribute("product", res);
        return "product-details";
    }

    @GetMapping("/products/{id}/edit")
    @PreAuthorize("hasAuthority ('ROLE_FARMER')")
    public String productEdit(@PathVariable(value = "id") int id,  Model model){
        if(!productRepository.existsById(id)){
            return "redirect:/products";
        }
        Optional<Product> product = productRepository.findById(id);
        List<Product> res = new ArrayList<>();
        product.ifPresent(res::add);
        model.addAttribute("product", res);
        return "product-edit";
    }

    @PostMapping("/products/{id}/edit")
    @PreAuthorize("hasAuthority ('ROLE_FARMER')")
    public String productPostUpdate(@PathVariable(value = "id") int id, @RequestParam String name, @RequestParam String price, @RequestParam String description,  Model model){
       Product product = productRepository.findById(id).orElseThrow();
       product.setName(name);
       product.setPrice(Integer.parseInt(price));
       product.setDescription(description);
       productRepository.save(product);
        return "redirect:/products";
    }

    @PostMapping("/products/{id}/remove")
    @PreAuthorize("hasAuthority ('ROLE_FARMER')")
    public String productPostDelete(@PathVariable(value = "id") int id, Model model){
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/products";
    }
}

