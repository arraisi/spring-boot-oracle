package com.itsarraisi.oracle.springboot.webapp.controller;

import com.itsarraisi.oracle.springboot.webapp.dao.CustomerDao;
import com.itsarraisi.oracle.springboot.webapp.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerDao dao;

    @GetMapping("/list")
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> value = dao.findAll();
        if(!value.isEmpty()) {
            return ResponseEntity.ok(value);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
