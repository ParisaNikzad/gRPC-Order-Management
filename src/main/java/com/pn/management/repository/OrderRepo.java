package com.pn.management.repository;

import com.pn.management.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Integer> {
}
