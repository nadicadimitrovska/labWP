package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    //Order placeOrder(String color, String size, String clientName, String address);
    Order placeOrder(String color, String size, String username, LocalDateTime dateCreated);
    List<Order> listAll();
    List<Order> findByUsername(String username);
    Optional<Order>findById(Long id);
    List<Order>findAllOrdersByDateBetween(LocalDateTime dateFrom, LocalDateTime dateTo);

}
