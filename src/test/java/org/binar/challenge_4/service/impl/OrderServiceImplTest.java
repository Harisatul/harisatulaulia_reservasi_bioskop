package org.binar.challenge_4.service.impl;

import net.sf.jasperreports.engine.JRException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.binar.challenge_4.entities.Order;
import org.binar.challenge_4.entities.Schedule;
import org.binar.challenge_4.entities.Seat;
import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.repository.OrderRepository;
import org.binar.challenge_4.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {


    @Mock
    private OrderRepository orderRepository;
    @Mock Order order;

    private OrderService orderService;


    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(orderRepository);
    }


    @Test
    void createOrder() throws JRException, FileNotFoundException {
        Order order = new Order();
        order.setId(1l);
        given(orderRepository.save(any(Order.class))).willReturn(order);
        orderService.createOrder(order);
        verify(orderRepository).save(order);

    }

    @Test
    void generateInvoice() throws JRException, FileNotFoundException {
//        Order order = new Order();
//        order.setId(1l);
        given(orderRepository.findById(1l)).willReturn(Optional.of(order));
        orderService.generateInvoice(1l);
        verify(orderRepository).findById(1l);
    }
}