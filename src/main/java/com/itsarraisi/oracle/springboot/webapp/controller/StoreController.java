package com.itsarraisi.oracle.springboot.webapp.controller;

import com.itsarraisi.oracle.springboot.webapp.model.Customer;
import com.itsarraisi.oracle.springboot.webapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.UUID;

@Controller
public class StoreController {
    @Autowired
    CustomerRepository repository;

    @GetMapping("/")
    public String halo(Model model, @ModelAttribute Customer customer) {
        model.addAttribute("customer", customer);
        Iterable<Customer> all = repository.findAll();
        model.addAttribute("listCustomer", all);
        return "index";
    }

    @PostMapping("/customer/save")
    public String saveCustomer(@RequestParam(required = false) String name) {
        String id = UUID.randomUUID().toString();
        String createdDate = new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.now()));

        if (name.isEmpty()){
            return "/500-Error";
        }
        Customer customer = repository.save(new Customer(id, name, createdDate));
        return "redirect:/";
    }
}
