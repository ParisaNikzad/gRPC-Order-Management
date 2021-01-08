package com.pn.management.model;

import com.pn.management.controller.OrderController;
import com.pn.management.repository.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(MockitoJUnitRunner.class)
class OrderTest {

    private Validator validator;

    @InjectMocks
    OrderController orderControllerMock;

    @Mock
    OrderRepo orderRepoMock;

    @Autowired
    Order order;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testNotNullDirection() throws SQLException {
        order = new Order("XMU", 0, 1.3, null);
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        assertFalse(violations.isEmpty());
    }

}