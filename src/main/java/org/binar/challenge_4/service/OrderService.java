package org.binar.challenge_4.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.binar.challenge_4.entities.Order;

import java.io.FileNotFoundException;

public interface OrderService {

    Order createOrder (Order order);

    JasperPrint generateInvoice(Long orderId) throws FileNotFoundException, JRException;

}
