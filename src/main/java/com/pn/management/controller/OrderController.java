package com.pn.management.controller;

import com.pn.management.model.Order;
import com.pn.management.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderRepo orderRepo;

    @RequestMapping("/order")
    public List<Order> getOrders(){
        return (List<Order>) orderRepo.findAll();
    }

    @GetMapping("/order/{order_id}")
    public Optional<Order> getOrder(@PathVariable("order_id") int order_id){
        return orderRepo.findById(order_id);
    }


    @PostMapping("/order")
    public Order addOrder(@Valid @RequestBody Order order){
        order.setStatus("RECEIVED WITH JSON");
        order.setOrderTime(new Date());
        orderRepo.save(order);
        return order;
    }
}
