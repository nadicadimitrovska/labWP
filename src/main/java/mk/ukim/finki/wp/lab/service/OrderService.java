package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(String color, String size, String clientName, String address);
    List<Order> listAll();
}
