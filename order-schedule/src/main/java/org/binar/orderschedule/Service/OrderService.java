package org.binar.orderschedule.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.binar.orderschedule.entities.Order;

import java.io.FileNotFoundException;

public interface OrderService {

    Order createOrder (Order order);

    JasperPrint generateInvoice(Long orderId) throws FileNotFoundException, JRException;

}
