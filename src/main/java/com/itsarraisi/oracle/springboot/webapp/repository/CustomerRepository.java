package com.itsarraisi.oracle.springboot.webapp.repository;

import com.itsarraisi.oracle.springboot.webapp.model.Customer;
import com.itsarraisi.oracle.springboot.webapp.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {
}
