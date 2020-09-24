package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ICrudRepo;
import com.example.demo.repository.ProductRepo;
//import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private ICrudRepo productRepo;

    /*@Autowired
    ProductService productService;*/



    public HomeController(){
        productRepo = new ProductRepo();
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("products", productRepo.readAll());
        return "index";
    }

    @GetMapping("/create")
    public String getCreate(Product product){
        return "create";
    }

    @PostMapping("/create")
    public String postCreate(Product product){

        productRepo.create(product);
        //productService.create(product);
        return "redirect:/";
    }

    @GetMapping("update")
    public String getUpdate(/*@PathVariable("id") int id*/@RequestParam int id, Model model){
       model.addAttribute("updateProduct", productRepo.read(id));
       return "../update";
    }

    @PostMapping("update")
    public String postUpdate(Product product){
        productRepo.update(product);
        return "redirect:/";
    }

    @GetMapping("delete")
    public String getDelete(@RequestParam int id, Model model){
        model.addAttribute("deleteProduct", productRepo.read(id));
        return "delete";
    }

    @PostMapping("delete")
    public String postDelete(int id){
        productRepo.delete(id);
        return "redirect:/";
    }
}
