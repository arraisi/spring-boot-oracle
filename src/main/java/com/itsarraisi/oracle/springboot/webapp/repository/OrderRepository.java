package com.itsarraisi.oracle.springboot.webapp.repository;

import com.itsarraisi.oracle.springboot.webapp.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, String> {
}
