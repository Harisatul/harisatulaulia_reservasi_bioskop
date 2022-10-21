package org.binar.challenge_4.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.binar.challenge_4.entities.Order;
import org.binar.challenge_4.entities.Seat;
import org.binar.challenge_4.exception.ResourceNotFoundException;
import org.binar.challenge_4.repository.OrderRepository;
import org.binar.challenge_4.repository.SeatRepository;
import org.binar.challenge_4.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;


    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
    @Override
    public JasperPrint generateInvoice(Long orderId) throws FileNotFoundException, JRException {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    log.error("ERROR : "+ "Email is already taken");
                    return new ResourceNotFoundException("Order", "id", orderId);
                });
        Map<String, Object> dataMap = dataParameter(order);
        List<Order> ordersCollect = new LinkedList<>();
        ordersCollect.add(order);
        dataMap.put("userData", new JRBeanCollectionDataSource(ordersCollect));
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(
                        ResourceUtils.getFile("invoice.jrxml")
                                .getAbsolutePath())
                , dataMap
                , new JREmptyDataSource()
        );
        log.info("Info :  generated invoice success");
        return jasperPrint;
    }

    public Map<String, Object> dataParameter(Order orders){
        String username = orders.getUser().getUsername();
        String movieStart = String.format(orders.getSchedule().getHourStart().toString(), "HH:MM");
        String movieEnd = String.format(orders.getSchedule().getHourEnd().toString(), "HH:MMta");
        String studioCode = orders.getSchedule().getStudio().getStudioCode();
        String movieTittle = orders.getSchedule().getMovies().getMovieTittle();
        String seat = orders.getSeats().getSeat_number().toString();

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("studioCode", studioCode);
        dataMap.put("userId", username);
        dataMap.put("movieTittle", movieTittle);
        dataMap.put("movieStart", movieStart);
        dataMap.put("movieEnd", movieEnd);
        dataMap.put("seat", seat);
        dataMap.put("id", "12212");
        log.info("Info :  mapping data from database success");
        return dataMap;
    }
}
