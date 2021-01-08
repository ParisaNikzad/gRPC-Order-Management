package com.pn.management.controller;

import com.pn.management.model.Order;
import com.pn.management.repository.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @InjectMocks
    OrderController orderControllerMock;

    @Mock
    OrderRepo orderRepoMock;

    Order order;
    Order addedOrder;


    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetOrder() {
        order = new Order();
        order.setTicker("As234");
        order.setQuantity(2);
        order.setPrice(99.9);
        order.setDirection("B");
        order.setOrderId(15);
        order.setOrderTime(new Date());
        order.setStatus("RECEIVED");
        when(orderRepoMock.findById(15)).thenReturn(Optional.of(order));
        Optional<Order> orderTest = orderControllerMock.getOrder(15);
        assertNotNull(orderRepoMock.findById(15));
        assertEquals(orderRepoMock.findById(15), orderTest);
    }

    @Test
    public void testAddOrder(){
        addedOrder = new Order("XT25", 5, 1.3, "S");
        addedOrder.setOrderId(16);
        addedOrder.setOrderTime(new Date());
        addedOrder.setStatus("RECEIVED");
        when(orderControllerMock.addOrder(addedOrder)).thenReturn(addedOrder);
        Order responseOrder = orderControllerMock.addOrder(addedOrder);
        assertEquals(orderControllerMock.addOrder(addedOrder), responseOrder);
    }

    @Test
    public void testNotFoundOrder(){
        when(orderRepoMock.findById(20)).thenReturn(null);
        Optional<Order> orderTest = orderControllerMock.getOrder(20);
        assertEquals(orderRepoMock.findById(20), orderTest);
    }

}
