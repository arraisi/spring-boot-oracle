package com.itsarraisi.oracle.springboot.webapp.repository;

import com.itsarraisi.oracle.springboot.webapp.model.Order;
import com.itsarraisi.oracle.springboot.webapp.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
}
