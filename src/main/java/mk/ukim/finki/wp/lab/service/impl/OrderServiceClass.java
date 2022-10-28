package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceClass implements OrderService {

    @Override
    public Order placeOrder(String balloonColor, String clientName, String address) {
        return null;
    }
}
